package me.rerere.rikkahub.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import me.rerere.rikkahub.R

private val RibbonTopHeight: Dp = 56.dp
private const val RIBBON_EDGE_ALPHA = 0.88f

@Composable
fun RibbonBackdrop(
    modifier: Modifier = Modifier,
) {
    val painter = painterResource(id = R.drawable.bg_ribbon_frame)

    // zIndex(10f) 强制让蕾丝画框永远浮在最顶层，不会被任何页面盖住，也不会挡手指点击
    Box(modifier = modifier.fillMaxSize().zIndex(10f)) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .height(RibbonTopHeight)
                .align(Alignment.TopCenter)
                .alpha(RIBBON_EDGE_ALPHA)
        )
    }
}
