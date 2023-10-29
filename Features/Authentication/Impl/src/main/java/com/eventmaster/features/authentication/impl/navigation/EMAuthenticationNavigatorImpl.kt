package com.eventmaster.features.authentication.impl.navigation

import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.eventmaster.features.authentication.api.navigation.EMAuthenticationNavigator

class EMAuthenticationNavigatorImpl(
    private val featureNavigator: EMFeatureNavigator
) : EMAuthenticationNavigator {

    override fun navigateToAuth() {
        featureNavigator.navigateTo(EMAuthenticationFeatureScreens.Auth)
    }

    override fun navigateToSignUp() {
        featureNavigator.navigateTo(EMAuthenticationFeatureScreens.SignUp)
    }

    override fun navigateToLogIn() {
        TODO("Not yet implemented")
    }
}