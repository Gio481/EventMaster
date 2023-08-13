package com.eventmaster.home.impl.navigation

import com.emcore_navigation.navigator.feature.EMFeatureInitializer
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen

object EMHomeInitializer : EMFeatureInitializer {

    override fun features(): List<EMFeatureScreen> = listOf(EMHomeFeatureScreens.Main)
}