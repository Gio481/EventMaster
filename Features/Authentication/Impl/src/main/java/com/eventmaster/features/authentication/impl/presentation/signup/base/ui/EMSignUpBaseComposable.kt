package com.eventmaster.features.authentication.impl.presentation.signup.base.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.base.ui.action.EMActionButton
import com.eventmaster.core.presentation.util.changeStatusBarColor
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.vm.EMSignUpBaseVm

@Composable
fun EMSignUpBaseComposable(
    vm: EMSignUpBaseVm,
    showBackButton: Boolean = true,
    currentStep: EMSignUpStepType,
    bottomActionButton: EMActionButton = EMActionButton.Single("Continue"),
    enableBottomActionContent: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        context.changeStatusBarColor(Color(0xFF121212))
    }
    EMScreenBase(
        vm = vm,
        showBackButton = showBackButton,
        showBottomAction = true,
        enableBottomActionContent = enableBottomActionContent,
        bottomActionButton = bottomActionButton,
        bottomAction = { vm.nextStep() },
        headerText = "Create Account",
        headerBackground = Color(0xFF121212)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
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
            content.invoke(this)
        }
    }
}

private fun getBackgroundColor(
    currentStep: EMSignUpStepType,
    signUpStepType: EMSignUpStepType
): Color {
    return when {
        currentStep.ordinal == signUpStepType.ordinal -> Color(0xFFEEEEEE)
        currentStep.ordinal > signUpStepType.ordinal -> Color(0xFF26BF7F)
        else -> Color(0xFF404040)
    }
}
