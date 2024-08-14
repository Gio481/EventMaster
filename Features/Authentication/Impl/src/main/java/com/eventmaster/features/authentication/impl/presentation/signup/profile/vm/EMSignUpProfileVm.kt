package com.eventmaster.features.authentication.impl.presentation.signup.profile.vm

import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm

class EMSignUpProfileVm : EMSignUpBaseVm() {

    override fun nextStep() {
        nextScreen(EMSignUpScreens.Success)
    }
}