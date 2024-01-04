package com.eventmaster.features.authentication.impl.presentation.signup.birthdate.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.components.textfield.datepicker.EMDatePickerTextField
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.birthdate.vm.EMSignUpBirthdateVm
import org.koin.androidx.compose.get

@Composable
fun EMSignUpBirthdate(vm: EMSignUpBirthdateVm = get()) {
    EMSignUpBaseComposable(
        vm = vm,
        currentStep = EMSignUpStepType.BIRTHDATE
    ) {
        EMDatePickerTextField(
            title = "Choose your birthdate \uD83C\uDF82",
            hint = "DD / MM / YYYY",
            modifier = Modifier.padding(top = 48.dp)
        )
    }
}