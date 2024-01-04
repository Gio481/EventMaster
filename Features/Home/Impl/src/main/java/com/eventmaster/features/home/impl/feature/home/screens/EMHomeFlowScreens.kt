package com.eventmaster.features.home.impl.feature.home.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.features.home.impl.presentation.home.ui.EMHomeComposable

sealed class EMHomeFlowScreens : EMFlowScreen() {

    object Home : EMHomeFlowScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "home2",
            content = { EMHomeComposable() }
        )
    }

}