package com.eventmaster.core.presentation.components.textfield

import androidx.compose.runtime.Composable
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig.DatePicker
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig.Password
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig.Regular
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig.Type
import com.eventmaster.core.presentation.components.textfield.datepicker.EMDatePickerTextField
import com.eventmaster.core.presentation.components.textfield.password.EMPasswordTextField
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField

@Composable
fun EMTextField(config: EMTextFieldConfig) {
    when (config.type) {
        Type.Password -> EMPasswordTextField(config as Password)
        Type.Regular -> EMRegularTextField(config as Regular)
        Type.DatePicker -> EMDatePickerTextField(config as DatePicker)
    }
}