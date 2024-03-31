package com.eventmaster.core.presentation.util

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun getImeHeight(): Dp {
    val density = LocalDensity.current
    return (WindowInsets.ime.getBottom(density) - WindowInsets.navigationBars.getBottom(density)).pxToDp()
}
@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun Dp.dpToInt() = with(LocalDensity.current) { this@dpToInt.toPx() }.toInt()