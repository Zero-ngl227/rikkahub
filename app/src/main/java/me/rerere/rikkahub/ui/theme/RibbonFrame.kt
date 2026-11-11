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

/** 上下两条框边的高度。想更明显就调大，想给内容让位就调小，68～120 之间随便试 */
val RibbonEdgeHeight: Dp = 88.dp

/** 框边的不透明度，0 到 1 */
private const val RIBBON_EDGE_ALPHA = 0.92f

/**
 * 洛可可画框的上下两条边。
 * 不是整张图铺满——竖屏手机铺方图会被裁掉两侧。
 * 只从原图的上边缘切一条、下边缘切一条，中间内容区保持干净。
 * 不拦截触摸，所以底下的按钮照样能点。
 */
@Composable
fun RibbonBackdrop(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize().zIndex(1f)) {
        Box(Modifier.align(Alignment.TopCenter)) {
            RibbonEdge(isTop = true)
        }
        Box(Modifier.align(Alignment.BottomCenter)) {
            RibbonEdge(isTop = false)
        }
    }
}

@Composable
private fun RibbonEdge(isTop: Boolean) {
    Box(Modifier.fillMaxWidth().height(RibbonEdgeHeight)) {
        Image(
            painter = painterResource(R.drawable.bg_ribbon_frame),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = if (isTop) Alignment.TopCenter else Alignment.BottomCenter,
            alpha = RIBBON_EDGE_ALPHA,
            modifier = Modifier.matchParentSize().clipToBounds(),
        )
        // 往内容区那一侧渐隐，免得边缘一刀切
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
