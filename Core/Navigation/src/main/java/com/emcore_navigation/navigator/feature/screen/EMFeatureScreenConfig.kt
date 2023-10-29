package com.emcore_navigation.navigator.feature.screen

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.base.screen.EMBaseScreenConfig

data class EMFeatureScreenConfig(
    override val route: String,
    override val argument: Argument? = null,
    override val showAnimation: Boolean = true,
    override val animation: Animation = featureAnimation(showAnimation),
    val featureStartDestination: String,
    val flowInitializer: (NavGraphBuilder) -> Unit
) : EMBaseScreenConfig()

