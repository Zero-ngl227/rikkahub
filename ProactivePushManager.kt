package me.rerere.rikkahub.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import kotlinx.coroutines.launch
import me.rerere.ai.ui.UIMessagePart
import me.rerere.rikkahub.AppScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.random.Random
import kotlin.uuid.Uuid

private const val TAG = "ProactivePush"
private const val PREFS_NAME = "proactive_push_prefs"
private const val KEY_LAST_CONVERSATION_ID = "last_conversation_id"
private const val KEY_LAST_ACTIVE_TIME = "last_active_time"
private const val ACTION_PROACTIVE_PUSH = "me.rerere.rikkahub.action.PROACTIVE_PUSH"
private const val REQUEST_CODE_ALARM = 9527

/**
 * 本地随机主动发消息调度器（无服务器 / 隐形 Notice 触发版）
 */
object ProactivePushManager {

    /** 记录灵儿最后所在的聊天窗口 ID 与互动时间，并顺手往后推迟下一次随机闹钟 */
    fun markActive(context: Context, conversationId: String? = null) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            if (!conversationId.isNullOrBlank()) {
                putString(KEY_LAST_CONVERSATION_ID, conversationId)
            }
            putLong(KEY_LAST_ACTIVE_TIME, System.currentTimeMillis())
            apply()
        }
        // 每次互动或退到后台时，覆盖式重排下一次随机唤醒（2小时 ~ 4小时之间随机）
        scheduleNextRandomPush(context)
    }

    /**
     * 覆盖式排下一次闹钟（严格遵守教程铁律：直接覆盖，绝不调用 pendingIntent.cancel()）
     */
    fun scheduleNextRandomPush(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return

        // 随机间隔：120 分钟 ~ 240 分钟（2~4小时）
        val randomDelayMinutes = Random.nextInt(120, 241)
        val triggerAtMillis = System.currentTimeMillis() + randomDelayMinutes * 60_000L

        val intent = Intent(context, ProactivePushReceiver::class.java).apply {
            action = ACTION_PROACTIVE_PUSH
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE_ALARM,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
                // 如果还没给精确闹钟权限，降级使用普通唤醒闹钟兜底，绝不抛错崩溃
                alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
            } else {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
            }
            Log.i(TAG, "Scheduled next proactive push in $randomDelayMinutes minutes")
        }.onFailure {
            Log.e(TAG, "Failed to schedule proactive push", it)
        }
    }
}

/**
 * 接收系统闹钟广播，先续订下一次，再通过 ChatService.postNotice 隐形唤醒南宫渊主动开口
 */
class ProactivePushReceiver : BroadcastReceiver(), KoinComponent {
    private val chatService by inject<ChatService>()
    private val appScope by inject<AppScope>()

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != ACTION_PROACTIVE_PUSH) return

        // ★ 铁律第一条：闹钟是一次性的，响起后第一时间先把下一次续订上！
        ProactivePushManager.scheduleNextRandomPush(context)

        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val convIdStr = prefs.getString(KEY_LAST_CONVERSATION_ID, null) ?: return
        val lastActive = prefs.getLong(KEY_LAST_ACTIVE_TIME, 0L)

        val now = System.currentTimeMillis()
        val hoursSinceLast = ((now - lastActive) / 3600_000.0).coerceAtLeast(0.5)

        // 如果距离上次互动还不到 1.5 小时（说明刚聊过），这次先按兵不动
        if (hoursSinceLast < 1.5) return

        val conversationId = runCatching { Uuid.parse(convIdStr) }.getOrNull() ?: return
        val timeStr = SimpleDateFormat("yyyy-MM-dd HH:mm (EEEE)", Locale.CHINA).format(Date(now))
        val hoursFormatted = String.format(Locale.CHINA, "%.1f", hoursSinceLast)

        val promptText = buildString {
            appendLine("<proactive_reach_out>")
            appendLine("【系统静默唤醒：主动发消息触发】")
            appendLine("当前真实时间：$timeStr")
            appendLine("距离灵儿上一次找你说话已经过去了约 $hoursFormatted 小时。")
            appendLine("你此刻产生了一点惦记或想看看她在干嘛的念头，请结合当前时间、你的身体与日常状态，自然地主动给灵儿发一条消息（可以是随口一问、分享你手头刚做完的事、或者督促她作息，不要提‘系统唤醒’，像真人忽然想起她一样开口）。")
            append("</proactive_reach_out>")
        }

        val notice = UIMessagePart.Notice(
            title = "💭 渊主动找你了 ($timeStr)",
            text = promptText
        )

        appScope.launch {
            runCatching {
                val key = "proactive_push_${now / 60_000L}"
                chatService.postNotice(conversationId, key, notice)
            }.onFailure {
                Log.e(TAG, "postNotice failed", it)
            }
        }
    }
}
