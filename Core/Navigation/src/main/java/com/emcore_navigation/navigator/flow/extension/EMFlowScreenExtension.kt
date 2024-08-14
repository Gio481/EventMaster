package com.emcore_navigation.navigator.flow.extension

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig

fun NavGraphBuilder.addFlowScreen(
    config: EMFlowScreenConfig,
) {
    composable(
        route = config.route,
        arguments = setArgument(config),
        enterTransition = { config.animation.enterTransition },
        exitTransition = { config.animation.exitTransition },
        popEnterTransition = { config.animation.popEnterTransition },
        popExitTransition = { config.animation.popExitTransition },
        content = config.content
    )
}

private fun setArgument(config: EMFlowScreenConfig): List<NamedNavArgument> {
    return config.argument?.let {
        listOf(navArgument(it.key) { type = it.type })
    } ?: emptyList()
}