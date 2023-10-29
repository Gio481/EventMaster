package com.eventmaster.features.splash.impl.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreenConfig
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.eventmaster.features.splash.impl.feature.main.init.EMSplashFlowInitializer

sealed class EMSplashFeatureScreens : EMFeatureScreen() {

    object Main : EMSplashFeatureScreens() {
        override fun config() = EMFeatureScreenConfig(
            route = "splash",
            featureStartDestination = "main",
            flowInitializer = { EMSplashFlowInitializer.init(it) }
        )
    }
}