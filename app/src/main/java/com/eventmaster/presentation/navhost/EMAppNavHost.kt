package com.eventmaster.presentation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilder
import com.emcore_navigation.navigator.nav_controller.EMAppNavController

@Composable
fun AppNavHost(
    navController: EMAppNavController,
    graphBuilder: EMAppGraphBuilder,
) {
    NavHost(navController = navController.getAppNavHostController(), graphBuilder.getAppNavGraph())
}