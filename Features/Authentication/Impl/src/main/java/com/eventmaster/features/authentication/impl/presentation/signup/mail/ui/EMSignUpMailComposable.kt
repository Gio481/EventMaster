package com.eventmaster.features.authentication.impl.presentation.signup.mail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        currentStep = EMSignUpStepType.MAIL
    ) {
        EMTextField(
            config = EMTextFieldConfig.Regular(
                title = "Let’s start with email",
                description = "To check if account is already created or not"
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { vm.nextStep() },
                shape = RoundedCornerShape(size = 15.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color(0xFF8F5FEA),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)

            ) {
                Alignment.Bottom
                Text(
                    text = "Continue",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}