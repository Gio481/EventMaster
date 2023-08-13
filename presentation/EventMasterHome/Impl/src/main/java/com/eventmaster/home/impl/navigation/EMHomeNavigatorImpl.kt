package com.eventmaster.home.impl.navigation

import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.eventmaster.home.api.navigation.EMHomeNavigator

class EMHomeNavigatorImpl(
    private val featureNavigator: EMFeatureNavigator
) : EMHomeNavigator {

    override fun navigateToHome() {
        featureNavigator.navigateTo(feature = EMHomeFeatureScreens.Main)
    }
}