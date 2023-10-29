package com.eventmaster.features.splash.impl.presentation.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.features.splash.impl.R
import com.eventmaster.features.splash.impl.presentation.main.vm.EMSplashVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMSplash() {
    EMScreenBase(vm = koinViewModel<EMSplashVm>(), showHeader = false) {
        Image(
            painter = painterResource(id = R.drawable.temporary_splash),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = Color.White,
                text = "Event Master",
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
            )
        }
    }
}