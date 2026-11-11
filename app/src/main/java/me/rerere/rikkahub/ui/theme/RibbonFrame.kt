package me.rerere.rikkahub.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import me.rerere.rikkahub.R

/** 顶部框边总高度。嫌挡顶栏就往下调，调成 0.dp 就关掉这一条 */
val RibbonTopHeight: Dp = 68.dp

/** 底部框边总高度。嫌挡输入框就往下调，调成 0.dp 就关掉这一条 */
val RibbonBottomHeight: Dp = 52.dp

/** 渐变起点。0.35 表示只露上面三成五、下面全化成背景；调到 0.9 就几乎不渐隐 */
private const val RIBBON_FADE_START = 0.35f

/** 框边不透明度，0 到 1 */
private const val RIBBON_EDGE_ALPHA = 0.92f

@Composable
fun RibbonBackdrop(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize().zIndex(1f)) {
        if (RibbonTopHeight > 0.dp) {
            Box(Modifier.align(Alignment.TopCenter)) {
                RibbonEdge(height = RibbonTopHeight, isTop = true)
            }
        }
        if (RibbonBottomHeight > 0.dp) {
            Box(Modifier.align(Alignment.BottomCenter)) {
                RibbonEdge(height = RibbonBottomHeight, isTop = false)
            }
        }
    }
}

@Composable
private fun RibbonEdge(height: Dp, isTop: Boolean) {
    Box(Modifier.fillMaxWidth().height(height)) {
        Image(
            painter = painterResource(R.drawable.bg_ribbon_frame),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = if (isTop) Alignment.TopCenter else Alignment.BottomCenter,
            alpha = RIBBON_EDGE_ALPHA,
            modifier = Modifier.matchParentSize().clipToBounds(),
        )
        Box(
            Modifier.matchParentSize().background(
                if (isTop) {
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        RIBBON_FADE_START to Color.Transparent,
                        1f to MaterialTheme.colorScheme.background
                    )
                } else {
                    Brush.verticalGradient(
                        0f to MaterialTheme.colorScheme.background,
                        1f - RIBBON_FADE_START to Color.Transparent,
                        1f to Color.Transparent
                    )
                }
            )
        )
    }
}
