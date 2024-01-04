package com.eventmaster.features.authentication.impl.presentation.auth.main.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.features.authentication.api.navigation.EMAuthenticationNavigator
import com.eventmaster.features.event.api.navigation.EMEventNavigator
import com.eventmaster.features.home.api.navigation.EMHomeNavigator

class EMAuthVm(
    private val navigator: EMAuthenticationNavigator,
    private val nav: EMEventNavigator,
    private val test: EMHomeNavigator
) : EMScreenBaseVm() {

    fun navigateToLogIn() {
        test.navigateToHome()
    }

    fun navigateToSignUp() {
        navigator.navigateToSignUp()
    }
}