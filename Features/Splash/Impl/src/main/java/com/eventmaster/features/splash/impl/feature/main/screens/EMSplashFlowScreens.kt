package com.eventmaster.features.splash.impl.feature.main.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.features.splash.impl.presentation.main.ui.EMSplash

sealed class EMSplashFlowScreens : EMFlowScreen() {

    object Main : EMSplashFlowScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "main",
            content = {
                EMSplash()
            }
        )
    }
}