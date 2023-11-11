package com.eventmaster.core.presentation.components.textfield.config

import androidx.compose.ui.Modifier


sealed class EMTextFieldConfig(internal val type: Type) {

    data class Regular(
        val title: String? = null,
        val hint: String = "",
        val description: String? = null,
        val modifier: Modifier = Modifier
    ) : EMTextFieldConfig(Type.Regular)

    data class Password(
        val title: String? = null,
        val hint: String = "",
        val description: String? = null,
        val withZootopiaAnimation: Boolean = true,
        val modifier: Modifier = Modifier
    ) : EMTextFieldConfig(Type.Password)

    data class DatePicker(
        val hint: String = "DD / MM / YYYY",
        val title: String? = null,
        val description: String? = null,
        val modifier: Modifier = Modifier
    ) : EMTextFieldConfig(Type.DatePicker)

    internal enum class Type {
        Regular,
        Password,
        DatePicker
    }
}