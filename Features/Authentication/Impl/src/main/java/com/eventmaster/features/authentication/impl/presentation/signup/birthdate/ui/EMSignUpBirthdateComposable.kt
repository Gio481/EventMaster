package com.eventmaster.features.authentication.impl.presentation.signup.birthdate.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.components.textfield.datepicker.EMDatePickerTextField
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.birthdate.vm.EMSignUpBirthdateVm
import org.koin.androidx.compose.get

@Composable
fun EMSignUpBirthdate(vm: EMSignUpBirthdateVm = get()) {
    val hint = rememberSaveable {
        mutableStateOf("DD / MM / YYYY")
    }
    EMSignUpBaseComposable(
        vm = vm,
        currentStep = EMSignUpStepType.BIRTHDATE,
        enableBottomActionContent = true
    ) {
        EMDatePickerTextField(
            title = "Choose your birthdate \uD83C\uDF82",
            hint = hint.value,
            modifier = Modifier.padding(top = 48.dp),
            doneAction = { item ->
                hint.value = "${item.firstColum} / ${item.secondColum} / ${item.thirdColum}"
            }
        )
    }
}