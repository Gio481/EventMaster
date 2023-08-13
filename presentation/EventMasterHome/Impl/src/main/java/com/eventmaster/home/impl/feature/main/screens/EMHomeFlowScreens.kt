package com.eventmaster.home.impl.feature.main.screens

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.EMFlowScreen
import com.emcore_navigation.navigator.flow.extension.addFlowScreen
import com.eventmaster.home.impl.presentation.main.ui.EMHome

sealed class EMHomeFlowScreens : EMFlowScreen() {

    object Main : EMHomeFlowScreens() {
        override val route: String = "main"
        override fun screen(navGraphBuilder: NavGraphBuilder) {
            navGraphBuilder.addFlowScreen(route) { EMHome() }
        }
    }
}