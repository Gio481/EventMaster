package com.eventmaster.features.authentication.impl.navigation

import androidx.navigation.NavType
import com.emcore_navigation.navigator.base.screen.EMBaseScreenConfig
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreenConfig
import com.eventmaster.features.authentication.impl.features.auth.init.EMAuthFlowInitializer
import com.eventmaster.features.authentication.impl.features.signup.init.EMSignUpFlowInitializer

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
            argument = EMBaseScreenConfig.Argument("gio", NavType.StringType),
            flowInitializer = { EMSignUpFlowInitializer.init(it) }
        )
    }

    object LogIn
}