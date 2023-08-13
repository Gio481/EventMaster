package com.emcore_navigation.navigator.nav_controller

import androidx.navigation.NavHostController
import androidx.navigation.NavigatorProvider

interface EMAppNavController {

    fun createAppNavHostController(controller: NavHostController)

    fun getAppNavHostController(): NavHostController

    fun geNavigatorProvider(): NavigatorProvider
}