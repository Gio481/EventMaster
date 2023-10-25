package com.eventmaster.features.splash.impl.navigation

import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.eventmaster.features.splash.api.navigation.EMSplashNavigator

class EMSplashNavigatorImpl(
    private val featureNavigator: EMFeatureNavigator
) : EMSplashNavigator {

    override fun navigateToSplash() {
        featureNavigator.navigateTo(
            feature = EMSplashFeatureScreens.Main
        )
    }
}