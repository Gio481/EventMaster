package com.eventmaster.features.splash.impl.presentation.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.R
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.util.changeStatusBarColor
import com.eventmaster.features.splash.impl.presentation.main.vm.EMSplashVm
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun EMSplash() {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        context.changeStatusBarColor(Color(0xFF140D28))
    }
    EMScreenBase(
        vm = koinViewModel<EMSplashVm>(),
        showHeader = false,
        modifier = Modifier
            .background(Color(0xFF140D28))
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(220.dp),
            painter = painterResource(id = R.drawable.em_splash_logo),
            contentDescription = "",
        )
    }
}