package com.eventmaster.features.authentication.impl.presentation.signup.password.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.components.textfield.password.EMPasswordTextField
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.password.vm.EMSignUpPasswordVm
import org.koin.androidx.compose.get

@Composable
fun EMSignUpPassword(vm: EMSignUpPasswordVm = get()) {
    EMSignUpBaseComposable(
        vm = vm,
        currentStep = EMSignUpStepType.PASSWORD
    ) {
        EMPasswordTextField(
            title = "Create password",
            description = "Remember, letters must be more than 6,also use symbols and digits",
            modifier = Modifier.padding(top = 48.dp)
        )
    }
}


