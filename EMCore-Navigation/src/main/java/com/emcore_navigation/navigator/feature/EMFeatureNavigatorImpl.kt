package com.emcore_navigation.navigator.feature

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.nav_controller.EMAppNavController

class EMFeatureNavigatorImpl(
    private val navController: EMAppNavController,
) : EMFeatureNavigator {

    override fun navigateTo(feature: EMFeatureScreen) {
        navController.getAppNavHostController().navigate(feature.route)
    }
}