package com.emcore_navigation.navigator.feature.extension

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.emcore_navigation.navigator.feature.screen.EMFeatureScreenConfig

fun NavGraphBuilder.addFeature(
    config: EMFeatureScreenConfig,
    builder: NavGraphBuilder.() -> Unit
) {
    navigation(
        config.featureStartDestination,
        config.route,
        enterTransition = { config.animation.enterTransition },
        exitTransition = { config.animation.exitTransition },
        popEnterTransition = { config.animation.popEnterTransition },
        popExitTransition = { config.animation.popExitTransition },
        builder = builder
    )
}