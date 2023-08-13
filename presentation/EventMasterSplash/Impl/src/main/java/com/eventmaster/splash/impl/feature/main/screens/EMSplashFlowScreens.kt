package com.eventmaster.splash.impl.feature.main.screens

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.EMFlowScreen
import com.emcore_navigation.navigator.flow.extension.addFlowScreen
import com.eventmaster.splash.impl.presentation.main.ui.EMSplash

sealed class EMSplashFlowScreens : EMFlowScreen() {

    object Main : EMSplashFlowScreens() {
        override val route: String = "main"
        override fun screen(navGraphBuilder: NavGraphBuilder) {
            navGraphBuilder.addFlowScreen(route) { EMSplash() }
        }
    }
}