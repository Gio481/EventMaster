package com.eventmaster.presentation.navhost

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilder
import com.emcore_navigation.navigator.nav_controller.EMAppNavController

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun AppNavHost(
    navController: EMAppNavController,
    graphBuilder: EMAppGraphBuilder,
) {
    NavHost(navController = navController.getAppNavHostController(), graphBuilder.getAppNavGraph())
}
