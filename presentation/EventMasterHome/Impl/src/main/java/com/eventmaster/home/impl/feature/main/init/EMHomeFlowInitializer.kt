package com.eventmaster.home.impl.feature.main.init

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.EMFlowInitializer
import com.eventmaster.home.impl.feature.main.screens.EMHomeFlowScreens

object EMHomeFlowInitializer : EMFlowInitializer {

    override fun init(navGraphBuilder: NavGraphBuilder) {
        EMHomeFlowScreens.Main.screen(navGraphBuilder)
    }
}