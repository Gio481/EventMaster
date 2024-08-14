package com.eventmaster.features.authentication.impl.presentation.signup.username.ui

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
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField
import com.eventmaster.core.presentation.extensions.EMPTY_TEXT
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.username.vm.EMSignUpUserNameVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMSignUpUserName(vm: EMSignUpUserNameVm = koinViewModel()) {
    var enableBottomActionContent by rememberSaveable { mutableStateOf(false) }
    var firstName by rememberSaveable { mutableStateOf(EMPTY_TEXT) }
    var lastName by rememberSaveable { mutableStateOf(EMPTY_TEXT) }
    var nickName by rememberSaveable { mutableStateOf(EMPTY_TEXT) }
    val fieldIsAlreadyValidated = vm.fieldIsAlreadyValidated.collectWithInitialValue()
    val textFieldValidatedState = vm.textFieldValidatedState.collectWithInitialValue()
    var description by rememberSaveable {
        mutableStateOf<String?>("This will display on your profile")
    }

    EMSignUpBaseComposable(
        vm = vm,
        currentStep = EMSignUpStepType.USERNAME,
        enableBottomActionContent = enableBottomActionContent
    ) {
        LaunchedEffect(
            key1 = textFieldValidatedState,
            key2 = firstName,
            key3 = lastName,
        ) {
            enableBottomActionContent = when {
                firstName.isEmpty() && lastName.isEmpty() -> false
                textFieldValidatedState is EMTextFieldValidatedState.Success || fieldIsAlreadyValidated -> true
                else -> false
            }
        }
        EMRegularTextField(
            title = "Enter your name",
            hint = "First name",
            modifier = Modifier.padding(top = 32.dp),
            onValueChange = {
                firstName = it.text
            }
        )
        EMRegularTextField(
            hint = "Last name",
            modifier = Modifier.padding(top = 16.dp),
            onValueChange = {
                lastName = it.text
            }
        )
        EMRegularTextField(
            hint = "Nickname",
            modifier = Modifier.padding(top = 16.dp),
            description = description,
            validatedState = textFieldValidatedState,
            onFocusChanged = {
                if (!it.hasFocus && !fieldIsAlreadyValidated) {
                    description = null
                    vm.validateNickname(nickName)
                }
            },
            onValueChange = {
                nickName = it.text
                vm.setFieldIsAlreadyValidatedValue(false)
                if (it.text.isEmpty()) {
                    description = "This will display on your profile"
                    vm.changeTextFieldValidatedState(EMTextFieldValidatedState.Default)
                }
            }
        )
    }
}