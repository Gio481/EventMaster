package com.eventmaster.features.eveny.impl.navigation

import com.emcore_navigation.navigator.feature.initializer.EMFeatureInitializer
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen

object EMEventInitializer : EMFeatureInitializer {
    override fun features(): List<EMFeatureScreen> {
        return listOf(
            EMEventFeatureScreens.Create
        )
    }
}