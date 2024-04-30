package com.eventmaster.features.authentication.impl.presentation.signup.mail.vm

import androidx.lifecycle.viewModelScope
import com.eventmaster.core.presentation.base.vm.stateWithValue
import com.eventmaster.core.presentation.components.textfield.base.model.EMTextFieldValidatedState
import com.eventmaster.core.presentation.components.textfield.base.model.EMTextFieldValidatedState.Default
import com.eventmaster.core.presentation.util.EMDrawable
import com.eventmaster.features.authentication.api.navigation.EMAuthenticationNavigator
import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm
import com.eventmaster.features.authentication.impl.presentation.signup.mail.ui.isEmail
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EMSignUpMailVm(
    private val authNavigator: EMAuthenticationNavigator
) : EMSignUpBaseVm() {

    val fieldIsAlreadyValidated = stateWithValue(false)
    val textFieldValidatedState = stateWithValue<EMTextFieldValidatedState>(Default)

    override fun nextStep() {
        setFieldIsAlreadyValidatedValue(true)
        nextScreen(EMSignUpScreens.UserName)
    }

    fun setFieldIsAlreadyValidatedValue(value: Boolean) {
        fieldIsAlreadyValidated.statePost(value)
    }


    fun gio(text: String) {
        viewModelScope.launch {
            if (!text.isEmail()) {
                textFieldValidatedState.statePost(
                    EMTextFieldValidatedState.Error(
                        text = "Enter the correct mail",
                        colorTintLongRes = 0xFFEA4335,
                        iconRes = EMDrawable.em_ic_error_circle_filled
                    )
                )
                return@launch
            }
            textFieldValidatedState.statePost(
                EMTextFieldValidatedState.Loading(
                    text = "Validating",
                    colorTintLongRes = 0xFFB4B4B4
                )
            )
            delay(1000)
            textFieldValidatedState.statePost(
                EMTextFieldValidatedState.Error(
                    iconRes = EMDrawable.em_ic_error_circle_filled,
                    colorTintLongRes = 0xFFEA4335,
                    text = "Email is already used. Do you want to [Log In]?",
                    spannableClickAction = {
                        setFieldIsAlreadyValidatedValue(true)
                        authNavigator.navigateToLogIn()
                    }
                )
            )
        }
    }

    fun setState(state: EMTextFieldValidatedState) {
        textFieldValidatedState.statePost(state)
    }

}