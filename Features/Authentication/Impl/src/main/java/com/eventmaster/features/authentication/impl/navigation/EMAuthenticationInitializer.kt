package com.eventmaster.features.authentication.impl.navigation

import com.emcore_navigation.navigator.feature.initializer.EMFeatureInitializer
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen

object EMAuthenticationInitializer : EMFeatureInitializer {
    override fun features(): List<EMFeatureScreen> {
        return listOf(
            EMAuthenticationFeatureScreens.Auth,
            EMAuthenticationFeatureScreens.SignUp,
            EMAuthenticationFeatureScreens.LogIn
        )
    }
}