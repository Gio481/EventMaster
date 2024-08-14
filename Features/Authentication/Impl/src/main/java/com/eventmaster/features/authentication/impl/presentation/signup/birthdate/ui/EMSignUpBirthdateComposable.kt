package com.eventmaster.features.authentication.impl.presentation.signup.birthdate.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.components.datepicker.model.EMDatePickerSelectedItem
import com.eventmaster.core.presentation.components.textfield.base.model.EMTextFieldValidatedState
import com.eventmaster.core.presentation.components.textfield.datepicker.EMDatePickerTextField
import com.eventmaster.core.presentation.extensions.EMPTY_TEXT
import com.eventmaster.core.presentation.util.getCurrentLocale
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.birthdate.vm.EMSignUpBirthdateVm
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun EMSignUpBirthdate(vm: EMSignUpBirthdateVm = koinViewModel()) {
    var enableBottomActionContent by rememberSaveable { mutableStateOf(false) }
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
        currentStep = EMSignUpStepType.BIRTHDATE,
        enableBottomActionContent = enableBottomActionContent
    ) {
        EMDatePickerTextField(
            title = "Choose your birthdate \uD83C\uDF82",
            hint = "DD / MM / YYYY",
            modifier = Modifier.padding(top = 48.dp),
            validatedState = textFieldValidatedState,
            doneAction = {
                vm.changeTextFieldValidatedState(EMTextFieldValidatedState.Success())
            }
        )
    }
}