package com.eventmaster.features.authentication.impl.features.login.init

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.initializer.EMFlowInitializer
import com.eventmaster.features.authentication.impl.features.login.screens.EMLogInFlowScreens

object EMLogInFlowInitializer : EMFlowInitializer {
    override fun init(navGraphBuilder: NavGraphBuilder) {
        EMLogInFlowScreens.Main.screen(navGraphBuilder)
    }
}