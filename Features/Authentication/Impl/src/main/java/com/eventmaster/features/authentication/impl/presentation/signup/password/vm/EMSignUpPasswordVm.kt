package com.eventmaster.features.authentication.impl.presentation.signup.password.vm

import com.eventmaster.core.presentation.components.textfield.base.model.EMTextFieldValidatedState
import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm

class EMSignUpPasswordVm : EMSignUpBaseVm() {

    override fun nextStep() {
        setFieldIsAlreadyValidatedValue(true)
        nextScreen(EMSignUpScreens.UserName)
    }

    fun validatePassword(password: String) {
        changeTextFieldValidatedState(
            when (password.length > 6) {
                true -> EMTextFieldValidatedState.Success()
                else -> EMTextFieldValidatedState.Error(text = "Use more than 6 symbols")
            }
        )
    }
}