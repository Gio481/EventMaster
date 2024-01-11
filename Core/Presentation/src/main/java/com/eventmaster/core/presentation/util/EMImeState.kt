package com.eventmaster.core.presentation.util

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity


@Composable
fun getImeHeight(): Int {
    val density = LocalDensity.current
    return WindowInsets.ime.getBottom(density) - WindowInsets.navigationBars.getBottom(density)
}