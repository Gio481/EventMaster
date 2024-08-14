package com.eventmaster.features.authentication.impl.presentation.signup.success.ui

import androidx.compose.runtime.Composable
import com.eventmaster.core.presentation.base.ui.success.ui.EMScreenBaseSuccessComposable
import com.eventmaster.features.authentication.impl.presentation.signup.success.vm.EMSignUpSuccessVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMSignUpSuccess(vm: EMSignUpSuccessVm = koinViewModel()) {
    EMScreenBaseSuccessComposable(vm)
}