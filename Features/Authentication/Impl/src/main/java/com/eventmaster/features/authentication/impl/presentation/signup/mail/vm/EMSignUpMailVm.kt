package com.eventmaster.features.authentication.impl.presentation.signup.mail.vm

import com.eventmaster.core.presentation.components.textfield.base.model.EMTextFieldValidatedState
import com.eventmaster.core.presentation.extensions.isEmail
import com.eventmaster.features.authentication.api.navigation.EMAuthenticationNavigator
import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm

class EMSignUpMailVm(
    private val authNavigator: EMAuthenticationNavigator
) : EMSignUpBaseVm() {

    override fun nextStep() {
        setFieldIsAlreadyValidatedValue(true)
        nextScreen(EMSignUpScreens.Password)
    }

    fun validateEmail(text: String) {
        changeTextFieldValidatedState(
            when {
                !text.isEmail() -> EMTextFieldValidatedState.Error(
                    text = "Email is already used. Do you want to [Log In]?",
                    spannableClickAction = {
                        setFieldIsAlreadyValidatedValue(true)
                        authNavigator.navigateToLogIn()
                    }
                )

                else -> EMTextFieldValidatedState.Success()
            }
        )
    }
}