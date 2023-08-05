package com.eventmaster.splash.impl.navigation

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.eventmaster.splash.impl.feature.main.init.EMSplashFlowScreensInitializer

sealed class EMSplashFeatureScreens : EMFeatureScreen() {

    object Main : EMSplashFeatureScreens() {
        override val route: String = "splash"
        override fun featureGraphBuilder() = NavGraphBuilder(navigatorProvider(), "main", route)
        override fun initFeature(graph: NavGraphBuilder) = EMSplashFlowScreensInitializer().init(graph)
    }
}