package com.eventmaster.features.authentication.impl.presentation.signup.base.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm

abstract class EMSignUpBaseVm : EMScreenBaseVm() {

    abstract fun nextStep()

}