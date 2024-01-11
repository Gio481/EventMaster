package com.eventmaster.features.authentication.impl.presentation.signup.birthdate.vm

import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm

class EMSignUpBirthdateVm : EMSignUpBaseVm() {

    override fun nextStep() {
        nextScreen(EMSignUpScreens.Profile)
    }
}