package com.eventmaster.core.presentation.components.datepicker.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import com.eventmaster.core.presentation.components.datepicker.state.EMDatePickerState
import java.util.Calendar
import java.util.TimeZone

@Composable
fun rememberPickerState() = remember { EMDatePickerState() }

internal fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }

@Composable
internal fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }

fun monthsList(): List<String> {
    return listOf(
        "January",
        "February",
        "Mart",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )
}

fun getLongAsDate(year: Int, month: Int, date: Int): Long {
    val timeZone = TimeZone.getTimeZone("UTC")
    val calendar = Calendar.getInstance(timeZone)
    calendar.set(Calendar.DAY_OF_MONTH, date)
    calendar.set(Calendar.MONTH, month - 1)
    calendar.set(Calendar.YEAR, year)

    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.timeInMillis
}

fun getDaysInMonth(month: Int, year: Int): Int {
    return if (month == 2) {
        28 + (if (year % 4 == 0) 1 else 0) - (if (year % 100 == 0) if (year % 400 == 0) 0 else 1 else 0)
    } else {
        31 - (month - 1) % 7 % 2
    }
}