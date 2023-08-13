package com.emcore_navigation.navigator.graph_builder

import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder

interface EMAppGraphBuilder {

    fun createAppNavGraphBuilder(startDestination: String)

    fun getAppNavGraphBuilder(): NavGraphBuilder

    fun getAppNavGraph(): NavGraph
}