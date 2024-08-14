package com.eventmaster.features.authentication.impl.presentation.signup.mail.ui

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
import com.eventmaster.core.presentation.components.textfield.base.model.EMTextFieldValidatedState.Success
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField
import com.eventmaster.core.presentation.extensions.EMPTY_TEXT
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.mail.vm.EMSignUpMailVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMSignUpMail(vm: EMSignUpMailVm = koinViewModel()) {
    var enableBottomActionContent by rememberSaveable { mutableStateOf(false) }
    var emailField by rememberSaveable { mutableStateOf(EMPTY_TEXT) }
    val fieldIsAlreadyValidated = vm.fieldIsAlreadyValidated.collectWithInitialValue()
    val textFieldValidatedState = vm.textFieldValidatedState.collectWithInitialValue()

    EMSignUpBaseComposable(
        vm = vm,
        showBackButton = false,
        currentStep = EMSignUpStepType.MAIL,
        enableBottomActionContent = enableBottomActionContent
    ) {
        LaunchedEffect(key1 = textFieldValidatedState, key2 = fieldIsAlreadyValidated) {
            enableBottomActionContent = when {
                textFieldValidatedState is Success || fieldIsAlreadyValidated -> true
                else -> false
            }
        }
        EMRegularTextField(
            title = "Let’s start with email",
            modifier = Modifier.padding(top = 32.dp),
            validatedState = textFieldValidatedState,
            onFocusChanged = {
                if (!it.hasFocus && !fieldIsAlreadyValidated) {
                    vm.validateEmail(emailField)
                }
            },
            onValueChange = {
                emailField = it.text
                vm.setFieldIsAlreadyValidatedValue(false)
                if (it.text.isEmpty()) {
                    vm.changeTextFieldValidatedState(EMTextFieldValidatedState.Default)
                }
            }
        )
    }
}