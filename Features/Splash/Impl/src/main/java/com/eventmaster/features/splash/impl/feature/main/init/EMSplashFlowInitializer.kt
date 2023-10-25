package com.eventmaster.features.splash.impl.feature.main.init

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.EMFlowInitializer
import com.eventmaster.features.splash.impl.feature.main.screens.EMSplashFlowScreens

object EMSplashFlowInitializer : EMFlowInitializer {
    override fun init(navGraphBuilder: NavGraphBuilder) {
        EMSplashFlowScreens.Main.screen(navGraphBuilder)
    }
}