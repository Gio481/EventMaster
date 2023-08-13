package com.eventmaster.home.impl.presentation.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.home.impl.presentation.main.ui.colors.Purple40

@Composable
fun EMHome() {
    Column(
        modifier = Modifier
            .background(Purple40)
            .fillMaxWidth()
            .height(200.dp)
            .padding(30.dp)
    ) {
        Text(
            text = "Hello",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }

}