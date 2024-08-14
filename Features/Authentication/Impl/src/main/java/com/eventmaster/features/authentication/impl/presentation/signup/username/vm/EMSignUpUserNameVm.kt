package com.eventmaster.features.authentication.impl.presentation.signup.username.vm

import com.eventmaster.core.presentation.components.textfield.base.model.EMTextFieldValidatedState
import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm

class EMSignUpUserNameVm : EMSignUpBaseVm() {

    override fun nextStep() {
        setFieldIsAlreadyValidatedValue(true)
        nextScreen(EMSignUpScreens.Birthdate)
    }

    fun validateNickname(nickname: String) {
        changeTextFieldValidatedState(EMTextFieldValidatedState.Success())
    }
}