package com.emcore_navigation.navigator.nav_controller

import androidx.navigation.NavHostController
import androidx.navigation.NavigatorProvider

class EMAppNavControllerImpl : EMAppNavController {

    private val navHostControllerMap = HashMap<Int, NavHostController>()

    override fun createAppNavHostController(controller: NavHostController) {
        navHostControllerMap[hashCode()] = controller
    }

    override fun getAppNavHostController(): NavHostController {
        return navHostControllerMap[hashCode()]!!
    }

    override fun geNavigatorProvider(): NavigatorProvider {
        return navHostControllerMap[hashCode()]!!.navigatorProvider
    }
}