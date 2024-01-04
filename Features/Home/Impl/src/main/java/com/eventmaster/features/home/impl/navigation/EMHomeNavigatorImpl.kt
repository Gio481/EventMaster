package com.eventmaster.features.home.impl.navigation

import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.eventmaster.features.home.api.navigation.EMHomeNavigator

class EMHomeNavigatorImpl(private val featureNavigator: EMFeatureNavigator) : EMHomeNavigator {

    override fun navigateToHome() {
        featureNavigator.navigateTo(EMHomeFeatureScreens.Home)
    }
}