package com.eventmaster.features.authentication.impl.presentation.signup.password.vm

import com.eventmaster.core.presentation.base.vm.stateWithValue
import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm

class EMSignUpPasswordVm : EMSignUpBaseVm() {

    val fieldIsAlreadyValidated = stateWithValue(false)

    override fun nextStep() {
        setFieldIsAlreadyValidatedValue(true)
        nextScreen(EMSignUpScreens.Birthdate)
    }

    fun setFieldIsAlreadyValidatedValue(value: Boolean) {
        fieldIsAlreadyValidated.statePost(value)
    }
}