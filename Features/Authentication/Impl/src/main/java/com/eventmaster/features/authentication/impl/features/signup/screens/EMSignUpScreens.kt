package com.eventmaster.features.authentication.impl.features.signup.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.features.authentication.impl.presentation.signup.mail.ui.EMSignUpMail
import com.eventmaster.features.authentication.impl.presentation.signup.password.ui.EMSignUpPassword

sealed class EMSignUpScreens : EMFlowScreen() {

    object Email : EMSignUpScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "email",
            content = {
                EMSignUpMail()
            }
        )
    }

    object Password : EMSignUpScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "password",
            showAnimation = true,
            content = { EMSignUpPassword() }
        )

    }

    object Birthdate

    object Profile
}