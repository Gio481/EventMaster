package com.eventmaster.features.authentication.impl.presentation.auth.main.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.features.authentication.api.navigation.EMAuthenticationNavigator

class EMAuthVm(
    private val navigator: EMAuthenticationNavigator
) : EMScreenBaseVm() {

    fun navigateToSignUp(){
        navigator.navigateToSignUp()
    }
}