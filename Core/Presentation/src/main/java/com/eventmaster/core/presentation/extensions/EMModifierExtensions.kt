package com.eventmaster.core.presentation.extensions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

fun Modifier.scrollIfNeeded(need: Boolean, nestedScrollSource: NestedScrollConnection): Modifier {
    return if (need) this.nestedScroll(nestedScrollSource) else this
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation =
            lerp(
                start = 0.95f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        alpha = transformation
        scaleY = transformation
    }

fun Modifier.fadingEdge(brush: Brush) = drawWithContent {
    drawContent()
    drawRect(brush = brush)
}

fun Modifier.clearFocusOnTap(focusManager: FocusManager) = this.pointerInput(Unit) {
    detectTapGestures(onTap = { focusManager.clearFocus() })
}

fun Modifier.disableContentClick() = this.pointerInput(Unit) {
    detectTapGestures()
}

fun Modifier.offsets(x: Int = 0, y: Int = 0): Modifier {
    return this.offset {
        IntOffset(
            x = x,
            y = y
        )
    }
}