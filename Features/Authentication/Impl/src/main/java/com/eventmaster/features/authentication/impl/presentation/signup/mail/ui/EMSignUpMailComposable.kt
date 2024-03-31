package com.eventmaster.features.authentication.impl.presentation.signup.mail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.mail.vm.EMSignUpMailVm
import org.koin.androidx.compose.get

@Composable
fun EMSignUpMail(vm: EMSignUpMailVm = get()) {
    val enableBottomActionContent = remember { mutableStateOf(false) }

    EMSignUpBaseComposable(
        vm = vm,
        showBackButton = false,
        currentStep = EMSignUpStepType.MAIL,
    ) {
        EMRegularTextField(
            title = "Let’s start with email",
            modifier = Modifier.padding(top = 32.dp),
            onFocusChanged = {
                if (!it.hasFocus) {
                    enableBottomActionContent.value = false
                }
            }
        )
    }
}