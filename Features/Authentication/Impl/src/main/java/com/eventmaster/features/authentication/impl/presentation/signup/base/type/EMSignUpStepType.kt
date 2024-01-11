package com.eventmaster.features.authentication.impl.presentation.signup.base.type

enum class EMSignUpStepType(val isLastStep: Boolean = false) {
    MAIL,
    USERNAME,
    PASSWORD,
    BIRTHDATE,
    PROFILE(true)
}