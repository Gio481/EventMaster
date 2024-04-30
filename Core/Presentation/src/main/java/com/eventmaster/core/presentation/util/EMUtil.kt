package com.eventmaster.core.presentation.util

import android.app.Activity
import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Context.changeStatusBarColor(color: Color) {
    val window = (this as Activity).window
    window.statusBarColor = color.toArgb()
}