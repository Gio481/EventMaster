package com.eventmaster.features.authentication.impl.presentation.signup.username.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.username.vm.EMSignUpUserNameVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMSignUpUserName(vm: EMSignUpUserNameVm = koinViewModel()) {
    EMSignUpBaseComposable(
        vm = vm,
        currentStep = EMSignUpStepType.USERNAME,
        enableBottomActionContent = true
    ) {
        EMRegularTextField(
            title = "Enter your name",
            hint = "First name",
            modifier = Modifier.padding(top = 32.dp)
        )
        EMRegularTextField(
            hint = "Last name",
            modifier = Modifier.padding(top = 16.dp)
        )
        EMRegularTextField(
            hint = "Nickname",
            modifier = Modifier.padding(top = 16.dp),
            description = "This will display on your profile"
        )
    }
}