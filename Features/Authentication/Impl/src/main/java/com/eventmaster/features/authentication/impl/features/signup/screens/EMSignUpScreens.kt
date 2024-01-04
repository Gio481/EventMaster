package com.eventmaster.features.authentication.impl.features.signup.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.features.authentication.impl.presentation.signup.birthdate.ui.EMSignUpBirthdate
import com.eventmaster.features.authentication.impl.presentation.signup.mail.di.mailModule
import com.eventmaster.features.authentication.impl.presentation.signup.mail.ui.EMSignUpMail
import com.eventmaster.features.authentication.impl.presentation.signup.password.ui.EMSignUpPassword
import com.eventmaster.features.authentication.impl.presentation.signup.profile.ui.EMSignUpProfile
import com.eventmaster.features.authentication.impl.presentation.signup.username.ui.EMSignUpUserName
import org.koin.core.context.loadKoinModules

sealed class EMSignUpScreens : EMFlowScreen() {

    object Email : EMSignUpScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "email",
            content = {
                EMSignUpMail()
            }
        )
    }

    object UserName : EMSignUpScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "userName",
            showAnimation = true,
            content = {
                EMSignUpUserName()
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

    object Birthdate : EMSignUpScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "birthdate",
            showAnimation = true,
            content = { EMSignUpBirthdate() }
        )
    }

    object Profile : EMSignUpScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "profile",
            showAnimation = true,
            content = { EMSignUpProfile() }
        )
    }
}