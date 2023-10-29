package com.eventmaster.features.splash.impl.presentation.main.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.features.authentication.api.navigation.EMAuthenticationNavigator
import kotlinx.coroutines.delay

class EMSplashVm(
    private val authenticationNavigator: EMAuthenticationNavigator
) : EMScreenBaseVm() {

    override suspend fun suspendOnCreate() {
        super.suspendOnCreate()
        delay(500)
        authenticationNavigator.navigateToAuth()
    }
}