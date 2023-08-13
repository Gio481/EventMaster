package com.eventmaster.home.impl.navigation

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.eventmaster.home.impl.feature.main.init.EMHomeFlowInitializer

sealed class EMHomeFeatureScreens : EMFeatureScreen() {

    object Main : EMHomeFeatureScreens() {
        override val route: String = "home"
        override fun featureGraphBuilder() = NavGraphBuilder(navigatorProvider(), "main", route)
        override fun initFeature(graph: NavGraphBuilder) = EMHomeFlowInitializer.init(graph)
    }
}