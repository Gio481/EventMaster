package com.eventmaster.features.authentication.impl.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreenConfig
import com.eventmaster.features.authentication.impl.features.auth.init.EMAuthFlowInitializer
import com.eventmaster.features.authentication.impl.features.login.init.EMLogInFlowInitializer
import com.eventmaster.features.authentication.impl.features.signup.init.EMSignUpFlowInitializer
import kotlinx.coroutines.DelicateCoroutinesApi

sealed class EMAuthenticationFeatureScreens : EMFeatureScreen() {

    object Auth : EMAuthenticationFeatureScreens() {
        override fun config() = EMFeatureScreenConfig(
            route = "auth",
            featureStartDestination = "main",
            flowInitializer = { EMAuthFlowInitializer.init(it) },
        )
    }

    object SignUp : EMAuthenticationFeatureScreens() {
        override fun config() = EMFeatureScreenConfig(
            route = "signUp/{gio}",
            featureStartDestination = "email",
            flowInitializer = { EMSignUpFlowInitializer.init(it) }
        )
    }

    object LogIn : EMAuthenticationFeatureScreens() {
        override fun config() = EMFeatureScreenConfig(
            route = "logIn",
            featureStartDestination = "main",
            flowInitializer = { EMLogInFlowInitializer.init(it) }
        )
    }
}