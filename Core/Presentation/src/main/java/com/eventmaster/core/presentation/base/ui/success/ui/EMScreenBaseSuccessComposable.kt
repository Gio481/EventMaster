package com.eventmaster.core.presentation.base.ui.success.ui

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.eventmaster.core.presentation.R
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.base.ui.action.EMActionButton
import com.eventmaster.core.presentation.base.ui.success.vm.EMScreenBaseSuccessVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMScreenBaseSuccessComposable(vm: EMScreenBaseSuccessVm) {
    EMScreenBase(
        vm = vm,
        showHeader = false,
        showBottomAction = true,
        bottomActionButton = EMActionButton.Single("close"),
        bottomAction = { vm.navigateTo() }
    ) {
        LottieAnimationFromJson(R.raw.em_lottie_success_animation)
    }
}

@Composable
fun BoxScope.LottieAnimationFromJson(jsonResId: Int) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(jsonResId)
    )
    val progress by animateLottieCompositionAsState(composition)

    LottieAnimation(
        composition = composition,
        modifier = Modifier
            .padding(bottom = 70.dp)
            .align(Alignment.TopCenter),
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
    )
    if ("%.1f".format(progress).toFloat() > 0.4f) {
        Text(
            text = "You are all set!",
            color = Color(0xFFEEEEEE),
            fontSize = 25.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 170.dp),
            fontWeight = FontWeight.Bold
        )
    }
}