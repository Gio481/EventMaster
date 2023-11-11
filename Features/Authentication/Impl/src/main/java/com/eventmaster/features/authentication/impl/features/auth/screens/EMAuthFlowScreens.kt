package com.eventmaster.features.authentication.impl.features.auth.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.features.authentication.impl.presentation.auth.main.ui.EMAuth
import kotlinx.coroutines.DelicateCoroutinesApi

sealed class EMAuthFlowScreens : EMFlowScreen() {

    @DelicateCoroutinesApi
    object Main : EMAuthFlowScreens() {
        override fun config() = EMFlowScreenConfig(
            route = "main",
            content = { EMAuth() }
        )
    }
}