package com.emcore_navigation.navigator.feature.screen

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.base.EMBaseScreen
import com.emcore_navigation.navigator.nav_controller.EMAppNavController
import org.koin.java.KoinJavaComponent.get


abstract class EMFeatureScreen : EMBaseScreen {
    protected fun navigatorProvider() = get<EMAppNavController>(EMAppNavController::class.java).geNavigatorProvider()

    abstract fun featureGraphBuilder(): NavGraphBuilder

    abstract fun initFeature(graph: NavGraphBuilder)
}

