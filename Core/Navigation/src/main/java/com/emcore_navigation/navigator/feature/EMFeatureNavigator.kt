package com.emcore_navigation.navigator.feature

import android.os.Bundle
import com.emcore_navigation.navigator.base.EMBaseNavigator
import com.emcore_navigation.navigator.base.screen.EMBaseScreen
import com.emcore_navigation.navigator.base.type.EMNavigatorType
import com.emcore_navigation.navigator.nav_controller.EMAppNavController

class EMFeatureNavigator(navController: EMAppNavController) : EMBaseNavigator(navController) {

    override fun navigatorType() = EMNavigatorType.FEATURE

    fun finishFeatureWithResult(screen: EMBaseScreen, result: Bundle = Bundle.EMPTY) {
        getNavResultCallback(getKey(screen))?.invoke(result)
        navHostController.popBackStack(screen.config().route, true)
    }

    fun finishFeature(screen: EMBaseScreen) {
        navHostController.popBackStack(screen.config().route, true)
    }
}