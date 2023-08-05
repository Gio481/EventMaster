package com.eventmaster.splash.impl.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen

object EMSplashInitializer {
    fun features(): List<EMFeatureScreen> = listOf(EMSplashFeatureScreens.Main)
}