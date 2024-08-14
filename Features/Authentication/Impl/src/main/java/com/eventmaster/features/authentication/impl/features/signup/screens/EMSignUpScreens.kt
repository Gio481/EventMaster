package com.eventmaster.features.authentication.impl.features.signup.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.core.presentation.base.ui.success.ui.EMScreenBaseSuccessComposable
import com.eventmaster.features.authentication.impl.presentation.signup.birthdate.ui.EMSignUpBirthdate
import com.eventmaster.features.authentication.impl.presentation.signup.mail.ui.EMSignUpMail
import com.eventmaster.features.authentication.impl.presentation.signup.password.ui.EMSignUpPassword
import com.eventmaster.features.authentication.impl.presentation.signup.profile.ui.EMSignUpProfile
import com.eventmaster.features.authentication.impl.presentation.signup.success.ui.EMSignUpSuccess
import com.eventmaster.features.authentication.impl.presentation.signup.username.ui.EMSignUpUserName

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
            showFlowTransitionAnimation = true,
            content = { EMSignUpPassword() }
        )
    }

    object UserName : EMSignUpScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "userName",
            showFlowTransitionAnimation = true,
            content = {
                EMSignUpUserName()
            }
        )
    }

    object Birthdate : EMSignUpScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "birthdate",
            showFlowTransitionAnimation = true,
            content = { EMSignUpBirthdate() }
        )
    }

    object Profile : EMSignUpScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "profile",
            showFlowTransitionAnimation = true,
            content = { EMSignUpProfile() }
        )
    }

    object Success : EMSignUpScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "success",
            showFlowTransitionAnimation = true,
            content = { EMSignUpSuccess() }
        )
    }
}