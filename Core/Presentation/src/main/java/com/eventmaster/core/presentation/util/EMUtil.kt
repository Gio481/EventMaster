package com.eventmaster.core.presentation.util

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.models.date.EMDateFormatType
import java.text.SimpleDateFormat
import java.util.Locale

fun Context.changeStatusBarColor(color: Color) {
    val window = (this as Activity).window
    window.statusBarColor = color.toArgb()
}

fun getCurrentLocale() = AppCompatDelegate.getApplicationLocales().get(0) ?: Locale.getDefault()

fun parseDate(stringDate: String, type: EMDateFormatType): Long? {
    val sdf = SimpleDateFormat(type.parsedFormat, getCurrentLocale())
    return sdf.parse(stringDate)?.time
}

fun formatDate(stringDate: String, type: EMDateFormatType): String {
    val sdf = SimpleDateFormat(type.format, getCurrentLocale())
    return sdf.format(parseDate(stringDate, type))
}

@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    resId: Int,
    text: String,
    tint: Color = Color(0xFFFFCD29),
    textModifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = "",
            tint = tint,
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color(0xDBEEEEEE),
            modifier = Modifier.padding(start = 4.dp, top = 1.dp),
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
        )
    }
}

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    resId: Int,
    text: String,
    tint: Color = Color(0xEDEEEEEE),
    fontSize: TextUnit = 16.sp
) {
    Row(modifier = modifier) {
        Text(
            text = text,
            fontSize = fontSize,
            color = Color(0xEDEEEEEE),
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
        )
        Icon(
            painter = painterResource(id = resId),
            contentDescription = "",
            tint = tint,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun ImageWithText(
    modifier: Modifier = Modifier,
    resId: Int,
    text: String
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = "",
            modifier = Modifier
                .size(14.dp)
                .clip(RoundedCornerShape(50))
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color(0xDBEEEEEE),
            modifier = Modifier.padding(start = 4.dp, top = 1.dp),
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
        )
    }
}

@Composable
fun fadeGradient() = Brush.verticalGradient(
    colors = listOf(Color(0x52151419), Color(0xED1A191B)),
    startY = with(LocalDensity.current) { 100.dp.toPx() },
    endY = with(LocalDensity.current) { 210.dp.toPx() }
)