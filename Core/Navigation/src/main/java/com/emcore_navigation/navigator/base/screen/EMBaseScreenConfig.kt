package com.emcore_navigation.navigator.base.screen

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavType

abstract class EMBaseScreenConfig {

    internal abstract val route: String
    internal abstract val showAnimation: Boolean
    internal abstract val argument: Argument?
    internal abstract val animation: Animation

    data class Argument(
        val key: String,
        val type: NavType<*>
    )

    data class Animation(
        val enterTransition: EnterTransition?,
        val popExitTransition: ExitTransition?,
        val popEnterTransition: EnterTransition? = EnterTransition.None,
        val exitTransition: ExitTransition? = null
    )

    companion object {
        fun featureAnimation(showAnimation: Boolean): Animation {
            return when (showAnimation) {
                false -> Animation(null, null, null, null)
                true -> Animation(
                    enterTransition = slideInVertically(
                        animationSpec = tween(200),
                        initialOffsetY = { it }
                    ),
                    popExitTransition = slideOutVertically(
                        tween(durationMillis = 200, easing = LinearEasing),
                        targetOffsetY = { it }
                    )
                )
            }
        }

        fun flowAnimation(showAnimation: Boolean): Animation {
            return when (showAnimation) {
                false -> Animation(null, null, null, null)
                true -> {
                    Animation(
                        enterTransition = slideInHorizontally(
                            animationSpec = tween(durationMillis = 200, easing = LinearEasing),
                            initialOffsetX = { it }
                        ),
                        popExitTransition = slideOutHorizontally(
                            animationSpec = tween(durationMillis = 200, easing = LinearEasing),
                            targetOffsetX = { it }
                        )
                    )
                }
            }
        }
    }
}