package com.eventmaster.features.authentication.impl.presentation.signup.mail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.components.textfield.EMTextField
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.mail.vm.EMSignUpMailVm
import org.koin.androidx.compose.get

@Composable
fun EMSignUpMail(vm: EMSignUpMailVm = get()) {
    EMSignUpBaseComposable(
        vm = vm,
        showBackButton = false,
        currentStep = EMSignUpStepType.MAIL,
    ) {
        EMTextField(
            config = EMTextFieldConfig.Regular(
                title = "Let’s start with email",
                description = "To check if account is already created or not",
                modifier = Modifier.padding(top = 48.dp)
            )
        )
    }
}