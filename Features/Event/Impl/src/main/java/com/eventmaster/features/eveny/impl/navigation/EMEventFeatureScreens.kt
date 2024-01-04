package com.eventmaster.features.eveny.impl.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreenConfig
import com.eventmaster.features.eveny.impl.features.create.init.EMCreateEventFlowInitializer

sealed class EMEventFeatureScreens : EMFeatureScreen() {

    object Create : EMFeatureScreen() {
        override fun config() = EMFeatureScreenConfig(
            route = "create",
            featureStartDestination = "cover",
            flowInitializer = { EMCreateEventFlowInitializer.init(it) }
        )
    }

    object Details
}