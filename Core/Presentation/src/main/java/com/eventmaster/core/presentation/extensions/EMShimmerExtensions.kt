package com.eventmaster.core.presentation.extensions

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.shimmerLoadingAnimation(
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1200,
): Modifier {
    val gradientBackground = background(
        brush = Brush.horizontalGradient(
            listOf(
                Color(0x1A9E9E9E),
                Color(0x269E9E9E),
                Color(0x429E9E9E),
            )
        )
    )
    return gradientBackground.composed {

        val shimmerColors = listOf(
            Color.Black.copy(alpha = 0.3f),
            Color.Black.copy(alpha = 0.5f),
            Color.Black.copy(alpha = 1.0f),
            Color.Black.copy(alpha = 0.5f),
            Color.Black.copy(alpha = 0.3f),
        )

        val transition = rememberInfiniteTransition()

        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart,
            )
        )

        this.background(
            brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                end = Offset(x = translateAnimation.value, y = angleOfAxisY),
            )
        )
    }
}

fun Modifier.shimmerGradient(): Modifier {
    return background(
        brush = Brush.horizontalGradient(
            listOf(
                Color(0x1A555454),
                Color(0x26818080),
                Color(0x429E9E9E),
            )
        )
    )
}

@Composable
fun listShimmer(count: Int, content: @Composable () -> Unit) {
    LazyColumn(userScrollEnabled = false) {
        items(count) { content.invoke() }
    }
}