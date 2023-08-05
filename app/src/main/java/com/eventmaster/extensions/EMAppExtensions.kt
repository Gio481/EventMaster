package com.eventmaster.extensions

import androidx.activity.ComponentActivity
import androidx.navigation.NavHostController
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilder
import com.emcore_navigation.navigator.nav_controller.EMAppNavController
import com.eventmaster.di.navigation.EMFeaturesInitializer
import org.koin.android.ext.android.get


fun ComponentActivity.initializeComponents(navHostController: NavHostController) {
    get<EMAppNavController>().createAppNavHostController(navHostController)
    get<EMAppGraphBuilder>().createAppNavGraphBuilder("splash")
    get<EMFeaturesInitializer>().init()
}