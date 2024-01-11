package com.eventmaster.features.group.impl.navigation

import com.emcore_navigation.navigator.feature.initializer.EMFeatureInitializer
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen

object EMGroupInitializer : EMFeatureInitializer {
    override fun features(): List<EMFeatureScreen> {
        return listOf(
            EMGroupFeatureScreens.Create,
            EMGroupFeatureScreens.Details
        )
    }
}