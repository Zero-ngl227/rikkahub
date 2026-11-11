package me.rerere.rikkahub.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateBottomPadding
import androidx.compose.foundation.layout.calculateTopPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
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

/** 顶部画框带的总高度（含状态栏那块）。嫌占地方就调小 */
val RibbonTopHeight: Dp = 64.dp

/** 底部画框带的总高度（含手势条那块）。嫌占地方就调小 */
val RibbonBottomHeight: Dp = 48.dp

/** 框边不透明度，0 到 1 */
private const val RIBBON_EDGE_ALPHA = 0.92f

/**
 * 画框图层。只有 visible 为 true 时才画。
 */
@Composable
fun RibbonBackdrop(visible: Boolean = true, modifier: Modifier = Modifier) {
    if (!visible) return
    Box(modifier.fillMaxSize().zIndex(1f)) {
        Box(Modifier.align(Alignment.TopCenter)) {
            RibbonEdge(height = RibbonTopHeight, isTop = true)
        }
        Box(Modifier.align(Alignment.BottomCenter)) {
            RibbonEdge(height = RibbonBottomHeight, isTop = false)
        }
    }
}

/**
 * 内容要往后退多少，才刚好让出画框带。
 * 顶栏自己会垫一层状态栏高度，所以要减掉，不然会重复让位、出现一道大空白。
 */
@Composable
fun ribbonContentPadding(visible: Boolean): PaddingValues {
    if (!visible) return PaddingValues(0.dp)
    val statusBar = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navBar = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    return PaddingValues(
        top = (RibbonTopHeight - statusBar).coerceAtLeast(0.dp),
        bottom = (RibbonBottomHeight - navBar).coerceAtLeast(0.dp),
    )
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
        // 朝内容区那侧渐隐，边缘不会一刀切
        Box(
            Modifier.matchParentSize().background(
                Brush.verticalGradient(
                    if (isTop) listOf(Color.Transparent, MaterialTheme.colorScheme.background)
                    else listOf(MaterialTheme.colorScheme.background, Color.Transparent)
                )
            )
        )
    }
}
