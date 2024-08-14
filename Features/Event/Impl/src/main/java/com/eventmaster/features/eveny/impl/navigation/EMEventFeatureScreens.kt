package com.eventmaster.features.eveny.impl.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreenConfig
import com.eventmaster.features.eveny.impl.features.create.init.EMCreateEventFlowInitializer
import com.eventmaster.features.eveny.impl.features.sections.init.EMEventsSectionsFlowInitializer

sealed class EMEventFeatureScreens : EMFeatureScreen() {

    object Create : EMEventFeatureScreens() {
        override fun config() = EMFeatureScreenConfig(
            route = "create",
            featureStartDestination = "cover",
            flowInitializer = { EMCreateEventFlowInitializer.init(it) }
        )
    }

    object Sections : EMEventFeatureScreens() {
        override fun config() = EMFeatureScreenConfig(
            route = "sections",
            featureStartDestination = "events_sections",
            flowInitializer = { EMEventsSectionsFlowInitializer.init(it) }
        )
    }
}