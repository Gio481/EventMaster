package com.eventmaster.features.group.impl.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreenConfig
import com.eventmaster.features.group.impl.features.create.init.EMCreateGroupFlowInitializer
import com.eventmaster.features.group.impl.features.groups.init.EMGroupDetailsFlowInitializer

sealed class EMGroupFeatureScreens : EMFeatureScreen() {

    object Create : EMGroupFeatureScreens() {
        override fun config() = EMFeatureScreenConfig(
            route = "createGroup",
            featureStartDestination = "groupCover",
            flowInitializer = { EMCreateGroupFlowInitializer.init(it) }
        )
    }

    object Details : EMGroupFeatureScreens() {
        override fun config() = EMFeatureScreenConfig(
            route = "groupDetailsFeature",
            featureStartDestination = "multiGroup",
            flowInitializer = { EMGroupDetailsFlowInitializer.init(it) }
        )
    }
}