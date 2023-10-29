package com.eventmaster.features.authentication.impl.presentation.signup.mail.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens

class EMSignUpMailVm : EMScreenBaseVm() {

    fun nextStep() {
        nextScreen(EMSignUpScreens.Password)
    }
}