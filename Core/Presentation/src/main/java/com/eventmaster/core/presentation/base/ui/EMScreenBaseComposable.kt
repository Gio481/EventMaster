package com.eventmaster.core.presentation.base.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.R
import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm

@Composable
fun EMScreenBase(
    vm: EMScreenBaseVm,
    showHeader: Boolean = true,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (showHeader){
                Image(
                    painter = painterResource(id = R.drawable.em_core_ic_arrow),
                    contentDescription = "arrow",
                    modifier = Modifier.padding(top = 2.dp)
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = "Create account",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Image(
                    painter = painterResource(id = R.drawable.em_core_ic_close),
                    contentDescription = "close",
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

        }
        Box {
            content.invoke()
        }
    }
    LaunchedEffect(key1 = null) {
        vm.onCreate()
        vm.suspendOnCreate()
    }
}