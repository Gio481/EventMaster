package com.eventmaster.features.home.impl.presentation.widgets.base.hexagon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun EMHexagon(
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(60.dp, 60.dp),
    paddingValues: PaddingValues = PaddingValues(0.dp),
    contentAlignment: Alignment = Alignment.Center,
    cornerRadius: Dp = 12.dp,
    strokeWidth: Dp = 6.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .size(size)
            .drawWithContent {
                drawContent()
                drawPath(
                    path = drawCustomHexagonPath(this.size),
                    color = Color(0xFF140D28),
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        pathEffect = PathEffect.cornerPathEffect(cornerRadius.toPx())
                    )
                )
            }
            .graphicsLayer {
                shape = EMHexagon()
                clip = true
            }
            .background(Color.Red)
            .then(modifier),
        contentAlignment = contentAlignment,
        content = content
    )
}

internal class EMHexagon : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(Path().apply { drawHexagon(size) })
}

class HexagonShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(drawCustomHexagonPath(size))
}

private fun drawCustomHexagonPath(size: Size): Path {
    return Path().apply {
//        val radius = (size.width / 2f).coerceAtMost(size.height / 2f)
//        customHexagon(radius, size)
        drawHexagon(size)
    }
}

private fun Path.drawHexagon(size: Size) {
    val height = size.height
    val width = size.width
    moveTo(width / 2f, 0f)
    lineTo(width, height / 4)
    lineTo(width, height / 4 * 3)
    lineTo(width / 2f, height)
    lineTo(0f, height / 4 * 3)
    lineTo(0f, height / 4)
    close()
}

private fun Path.customHexagon(radius: Float, size: Size) {
    val triangleHeight = (kotlin.math.sqrt(3.0) * radius / 2)
    val centerX = size.width / 2
    val centerY = size.height / 2
    moveTo(centerX, centerY + radius)
    lineTo((centerX - triangleHeight).toFloat(), centerY + radius / 2)
    lineTo((centerX - triangleHeight).toFloat(), centerY - radius / 2)
    lineTo(centerX, centerY - radius)
    lineTo((centerX + triangleHeight).toFloat(), centerY - radius / 2)
    lineTo((centerX + triangleHeight).toFloat(), centerY + radius / 2)
    close()
}