package com.eventmaster.features.group.impl.presentation.groups.details.ui.util

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconWithText(resId: Int, text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = "",
            tint = Color(0xFFFFCD29),
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