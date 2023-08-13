package com.eventmaster.splash.impl.presentation.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.eventmaster.splash.impl.R
import com.eventmaster.splash.impl.presentation.main.vm.EMSplashVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMSplash(vm: EMSplashVm = koinViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_logo),
            contentDescription = null
        )
        vm.navigate()
    }
}