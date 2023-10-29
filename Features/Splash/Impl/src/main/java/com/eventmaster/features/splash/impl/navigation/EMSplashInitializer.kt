package com.eventmaster.features.splash.impl.navigation

import com.emcore_navigation.navigator.feature.initializer.EMFeatureInitializer
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen

object EMSplashInitializer : EMFeatureInitializer {

    override fun features(): List<EMFeatureScreen> {
        return listOf(EMSplashFeatureScreens.Main)
    }
}