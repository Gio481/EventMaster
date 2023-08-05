package com.eventmaster.splash.impl.navigation

import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.eventmaster.splash.api.navigation.EMSplashNavigator

class EMSplashNavigatorImpl(
    private val featureNavigator: EMFeatureNavigator
) : EMSplashNavigator {

    override fun navigateToSplash() {
        featureNavigator.navigateTo(
            feature = EMSplashFeatureScreens.Main
        )
    }
}