package com.eventmaster.splash.impl.feature.main.init

import androidx.navigation.NavGraphBuilder
import com.eventmaster.splash.impl.feature.main.screens.EMSplashFlowScreens

class EMSplashFlowScreensInitializer {
    fun init(navGraphBuilder: NavGraphBuilder) {
        EMSplashFlowScreens.Main.screen(navGraphBuilder)
    }
}