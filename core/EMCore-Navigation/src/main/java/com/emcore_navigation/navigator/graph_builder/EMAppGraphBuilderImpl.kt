package com.emcore_navigation.navigator.graph_builder

import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.nav_controller.EMAppNavController

class EMAppGraphBuilderImpl(
    private val navHostController: EMAppNavController
) : EMAppGraphBuilder {

    private val navGraphBuilderList = HashMap<Int, NavGraphBuilder>()

    override fun createAppNavGraphBuilder(startDestination: String) {
        NavGraphBuilder(
            provider = navHostController.geNavigatorProvider(),
            startDestination = startDestination,
            route = null
        ).also {
            navGraphBuilderList[hashCode()] = it
        }
    }

    override fun getAppNavGraphBuilder(): NavGraphBuilder {
        return navGraphBuilderList[hashCode()]!!
    }

    override fun getAppNavGraph(): NavGraph {
        return navGraphBuilderList[hashCode()]!!.build()
    }

}