package com.eventmaster.features.home.impl.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreenConfig
import com.eventmaster.features.home.impl.feature.home.init.EMHomeFlowInitializer

sealed class EMHomeFeatureScreens :EMFeatureScreen(){

    object Home : EMHomeFeatureScreens() {
        override fun config() = EMFeatureScreenConfig(
            route = "home",
            featureStartDestination = "home2",
            flowInitializer = { EMHomeFlowInitializer.init(it) }
        )
    }
}