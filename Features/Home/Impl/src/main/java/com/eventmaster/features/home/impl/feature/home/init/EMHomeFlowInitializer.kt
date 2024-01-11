package com.eventmaster.features.home.impl.feature.home.init

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.initializer.EMFlowInitializer
import com.eventmaster.features.home.impl.feature.home.screens.EMHomeFlowScreens

object EMHomeFlowInitializer: EMFlowInitializer {
    override fun init(navGraphBuilder: NavGraphBuilder) {
        EMHomeFlowScreens.Home.screen(navGraphBuilder)
    }
}