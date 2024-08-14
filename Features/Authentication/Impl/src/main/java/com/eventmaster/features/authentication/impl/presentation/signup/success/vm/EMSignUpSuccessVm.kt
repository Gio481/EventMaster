package com.eventmaster.features.authentication.impl.presentation.signup.success.vm

import com.eventmaster.core.presentation.base.ui.success.vm.EMScreenBaseSuccessVm
import com.eventmaster.features.home.api.navigation.EMHomeNavigator

class EMSignUpSuccessVm(private val homeNavigator: EMHomeNavigator) : EMScreenBaseSuccessVm() {
    override fun navigateTo() {
        homeNavigator.navigateToHome()
    }
}