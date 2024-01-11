package com.eventmaster.features.authentication.impl.presentation.signup.username.vm

import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm

class EMSignUpUserNameVm : EMSignUpBaseVm() {

    override fun nextStep() {
        nextScreen(EMSignUpScreens.Password)
    }
}