package com.eventmaster.features.home.impl.navigation

import com.emcore_navigation.navigator.feature.initializer.EMFeatureInitializer
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen

object EMHomeInitializer : EMFeatureInitializer {

    override fun features(): List<EMFeatureScreen> {
        return listOf(EMHomeFeatureScreens.Home)
    }
}