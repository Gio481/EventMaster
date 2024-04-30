package com.eventmaster.features.authentication.impl.presentation.signup.password.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.components.textfield.base.model.EMTextFieldValidatedState
import com.eventmaster.core.presentation.components.textfield.password.EMPasswordTextField
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.password.vm.EMSignUpPasswordVm
import org.koin.androidx.compose.get

@Composable
fun EMSignUpPassword(vm: EMSignUpPasswordVm = get()) {
    var enableBottomActionContent by rememberSaveable { mutableStateOf(false) }
    var textFieldValidatedState by rememberSaveable {
        mutableStateOf<EMTextFieldValidatedState>(EMTextFieldValidatedState.Default)
    }
    var passwordField by rememberSaveable { mutableStateOf("") }
    val fieldIsAlreadyValidated = vm.fieldIsAlreadyValidated.collectWithInitialValue()

    LaunchedEffect(key1 = textFieldValidatedState, key2 = fieldIsAlreadyValidated) {
        enableBottomActionContent = when {
            textFieldValidatedState is EMTextFieldValidatedState.Success || fieldIsAlreadyValidated -> true
            else -> false
        }
    }

    EMSignUpBaseComposable(
        vm = vm,
        currentStep = EMSignUpStepType.PASSWORD
    ) {
        EMPasswordTextField(
            title = "Create password",
            description = "Remember, letters must be more than 6,also use symbols and digits",
            validatedState = textFieldValidatedState,
            modifier = Modifier.padding(top = 48.dp),
            onFocusChanged = {
                if (!it.hasFocus && !fieldIsAlreadyValidated) {
                    textFieldValidatedState = when (passwordField.length > 6) {
                        true -> EMTextFieldValidatedState.Success()
                        else -> EMTextFieldValidatedState.Error(text = "Use more than 6 symbols")
                    }
                    enableBottomActionContent = passwordField.length > 6
                }
            },
            onValueChange = {
                passwordField = it.text
                vm.setFieldIsAlreadyValidatedValue(false)
                if (it.text.isEmpty()) {
                    textFieldValidatedState = EMTextFieldValidatedState.Default
                }
            }
        )
    }
}


