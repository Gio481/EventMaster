package com.eventmaster.core.presentation.components.textfield

import androidx.compose.runtime.Composable
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig
import com.eventmaster.core.presentation.components.textfield.password.EMPasswordTextField
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField

@Composable
fun EMTextField(config: EMTextFieldConfig) {
    when (config.type) {
        EMTextFieldConfig.Type.Password -> EMPasswordTextField(config as EMTextFieldConfig.Password)
        EMTextFieldConfig.Type.Regular -> EMRegularTextField(config as EMTextFieldConfig.Regular)
    }
}