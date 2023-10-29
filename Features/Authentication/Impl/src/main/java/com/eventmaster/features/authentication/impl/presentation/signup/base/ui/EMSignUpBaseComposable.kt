package com.eventmaster.features.authentication.impl.presentation.signup.base.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType

@Composable
fun EMSignUpBaseComposable(
    vm: EMScreenBaseVm,
    currentStep: EMSignUpStepType,
    content: @Composable () -> Unit
) {
    EMScreenBase(vm) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp),
            ) {
                EMSignUpStepType.values().forEach { type ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(getBackgroundColor(currentStep, type))
                            .height(2.dp)
                    )
                    Spacer(modifier = Modifier.width(if (type.isLastStep) 0.dp else 15.dp))
                }
            }
            content.invoke()
        }
    }
}

private fun getBackgroundColor(
    currentStep: EMSignUpStepType,
    signUpStepType: EMSignUpStepType
): Color {
    return when {
        currentStep.ordinal == signUpStepType.ordinal -> Color.White
        currentStep.ordinal > signUpStepType.ordinal -> Color.Green
        else -> Color(0xFF454545)
    }
}
