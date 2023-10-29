package com.eventmaster.core.presentation.components.textfield.config


sealed class EMTextFieldConfig(internal val type: Type) {

    data class Regular(
        val title: String? = null,
        val hint: String? = null,
        val description: String? = null
    ) : EMTextFieldConfig(Type.Regular)

    data class Password(
        val title: String? = null,
        val hint: String? = null,
        val description: String? = null,
        val withZootopiaAnimation: Boolean = true,
    ) : EMTextFieldConfig(Type.Password)

    internal enum class Type {
        Regular, Password
    }
}