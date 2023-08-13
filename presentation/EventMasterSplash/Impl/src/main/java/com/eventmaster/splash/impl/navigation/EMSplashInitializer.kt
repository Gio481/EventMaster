package com.eventmaster.splash.impl.navigation

import com.emcore_navigation.navigator.feature.EMFeatureInitializer
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen

object EMSplashInitializer : EMFeatureInitializer {

    override fun features(): List<EMFeatureScreen> = listOf(EMSplashFeatureScreens.Main)
}