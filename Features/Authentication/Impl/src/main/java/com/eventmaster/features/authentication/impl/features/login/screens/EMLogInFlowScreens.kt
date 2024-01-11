package com.eventmaster.features.authentication.impl.features.login.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.features.authentication.impl.presentation.login.main.ui.EMLogIn

sealed class EMLogInFlowScreens : EMFlowScreen() {

    object Main : EMLogInFlowScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "main",
            content = { EMLogIn() }
        )
    }
}