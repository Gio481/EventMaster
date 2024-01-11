package com.eventmaster.features.authentication.impl.presentation.signup.mail.vm

import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm

class EMSignUpMailVm : EMSignUpBaseVm() {

    override fun nextStep() {
        nextScreen(EMSignUpScreens.UserName)
    }
}