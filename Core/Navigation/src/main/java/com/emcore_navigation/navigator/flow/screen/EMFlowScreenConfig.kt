package com.emcore_navigation.navigator.flow.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.emcore_navigation.navigator.base.screen.EMBaseScreenConfig

data class EMFlowScreenConfig(
    override val route: String,
    override val argument: Argument? = null,
    override val showAnimation: Boolean = false,
    override val animation: Animation = flowAnimation(showAnimation),
    val isStarterScreen: Boolean = false,
    val content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) : EMBaseScreenConfig()