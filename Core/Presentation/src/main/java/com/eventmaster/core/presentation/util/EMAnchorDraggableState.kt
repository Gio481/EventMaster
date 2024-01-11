package com.eventmaster.core.presentation.util

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class EMAnchoredDraggableCardState {
    DRAGGED_DOWN, DRAGGED_UP
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun anchoredDraggableState(
    positionalThresholdMultiply: Float = 0.5f,
    draggedDownAnchorTopDp: Dp = 200.dp,
): AnchoredDraggableState<EMAnchoredDraggableCardState> {
    val density = LocalDensity.current
    val anchoredDraggableState = remember {
        getAnchoredDraggableState(
            density,
            positionalThresholdMultiply,
            draggedDownAnchorTopDp
        )
    }
    return anchoredDraggableState
}

@OptIn(ExperimentalFoundationApi::class)
fun getAnchoredDraggableState(
    density: Density,
    positionalThresholdMultiply: Float = 0.5f,
    draggedDownAnchorTopDp: Dp = 200.dp,
): AnchoredDraggableState<EMAnchoredDraggableCardState> {
    val draggedDownAnchorTop = with(density) { draggedDownAnchorTopDp.toPx() }
    val anchors = DraggableAnchors {
        EMAnchoredDraggableCardState.DRAGGED_DOWN at draggedDownAnchorTop
        EMAnchoredDraggableCardState.DRAGGED_UP at 0f
    }
    return AnchoredDraggableState(
        initialValue = EMAnchoredDraggableCardState.DRAGGED_DOWN,
        anchors = anchors,
        positionalThreshold = { distance: Float -> distance * positionalThresholdMultiply },
        velocityThreshold = { with(density) { 100.dp.toPx() } },
        animationSpec = tween(),
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun getAnchoredDraggableStateProgress(
    anchoredDraggableState: AnchoredDraggableState<EMAnchoredDraggableCardState>,
    draggedDownAnchorTopDp: Dp = 200.dp
): Float {
    val draggedDownAnchorTop = with(LocalDensity.current) { draggedDownAnchorTopDp.toPx() }
    val offset = if (anchoredDraggableState.offset.isNaN()) 0f else anchoredDraggableState.offset
    return (1 - (offset / draggedDownAnchorTop)).coerceIn(0f, 1f)
}













