package com.eventmaster.core.presentation.components.textfield.model

data class EMTextFieldValidation(
    val regex: Regex,
    val errorMessage: String
)