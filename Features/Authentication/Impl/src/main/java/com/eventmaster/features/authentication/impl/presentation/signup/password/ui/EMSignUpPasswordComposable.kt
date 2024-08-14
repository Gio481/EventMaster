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
import com.eventmaster.core.presentation.extensions.EMPTY_TEXT
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.password.vm.EMSignUpPasswordVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMSignUpPassword(vm: EMSignUpPasswordVm = koinViewModel()) {
    var enableBottomActionContent by rememberSaveable { mutableStateOf(false) }
    var passwordField by rememberSaveable { mutableStateOf(EMPTY_TEXT) }
    var description by rememberSaveable {
        mutableStateOf<String?>("Remember, letters must be more than 6")
    }

    val fieldIsAlreadyValidated = vm.fieldIsAlreadyValidated.collectWithInitialValue()
    val textFieldValidatedState = vm.textFieldValidatedState.collectWithInitialValue()

    LaunchedEffect(key1 = textFieldValidatedState, key2 = fieldIsAlreadyValidated) {
        enableBottomActionContent = when {
            textFieldValidatedState is EMTextFieldValidatedState.Success || fieldIsAlreadyValidated -> true
            else -> false
        }
    }

    EMSignUpBaseComposable(
        vm = vm,
        currentStep = EMSignUpStepType.PASSWORD,
        enableBottomActionContent = enableBottomActionContent
    ) {
        EMPasswordTextField(
            title = "Create password",
            description = description,
            validatedState = textFieldValidatedState,
            modifier = Modifier.padding(top = 48.dp),
            onFocusChanged = {
                if (!it.hasFocus && !fieldIsAlreadyValidated) {
                    description = null
                    vm.validatePassword(passwordField)
                }
            },
            onValueChange = {
                passwordField = it.text
                vm.setFieldIsAlreadyValidatedValue(false)
                if (it.text.isEmpty()) {
                    description = "Remember, letters must be more than 6"
                    vm.changeTextFieldValidatedState(EMTextFieldValidatedState.Default)
                }
            }
        )
    }
}


