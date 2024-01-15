package com.eventmaster.features.group.impl.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun fadeGradient() = Brush.verticalGradient(
    colors = listOf(Color(0x52151419), Color(0xED1A191B)),
    startY = with(LocalDensity.current) { 100.dp.toPx() },
    endY = with(LocalDensity.current) { 210.dp.toPx() }
)

fun plusFriendsText() = Brush.linearGradient(
    0f to Color(0xFFD7D1EA),
    1f to Color(0xFFA080FF)
)