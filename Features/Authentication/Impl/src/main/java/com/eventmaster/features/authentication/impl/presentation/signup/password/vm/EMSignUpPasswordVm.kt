package com.eventmaster.features.authentication.impl.presentation.signup.password.vm

import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm

class EMSignUpPasswordVm : EMSignUpBaseVm() {

    override fun nextStep() {
        nextScreen(EMSignUpScreens.Birthdate)
    }
}