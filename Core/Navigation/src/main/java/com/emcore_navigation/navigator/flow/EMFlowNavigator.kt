package com.emcore_navigation.navigator.flow

import com.emcore_navigation.navigator.base.EMBaseNavigator
import com.emcore_navigation.navigator.base.screen.EMBaseScreen
import com.emcore_navigation.navigator.base.type.EMNavigatorType
import com.emcore_navigation.navigator.nav_controller.EMAppNavController

class EMFlowNavigator(navController: EMAppNavController) : EMBaseNavigator(navController) {
    override fun navigatorType() = EMNavigatorType.FLOW

    fun nextScreen(screen: EMBaseScreen){
        navigateTo(screen)
    }
}