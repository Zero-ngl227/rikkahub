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
import me.rerere.rikkahub.R

private val RibbonTopHeight: Dp = 56.dp
private const val RIBBON_EDGE_ALPHA = 0.85f // 蕾丝边本身带透明底，0.85f 既清晰又柔和；想全实心可改 1.0f

@Composable
fun RibbonBackdrop(
    modifier: Modifier = Modifier,
) {
    val painter = painterResource(id = R.drawable.bg_ribbon_frame)

    Box(modifier = modifier.fillMaxSize()) {
        // 顶部蕾丝边：左右100%铺满，高度维持 56.dp
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
