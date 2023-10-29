package com.emcore_navigation.navigator.flow.screen

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.emcore_navigation.navigator.base.screen.EMBaseScreen
import com.emcore_navigation.navigator.flow.extension.addFlowScreen
import com.emcore_navigation.navigator.nav_controller.EMAppNavController
import org.koin.java.KoinJavaComponent.get

abstract class EMFlowScreen : EMBaseScreen {

    private val config get() = config() as EMFlowScreenConfig

    fun screen(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.addFlowScreen(config)
    }

    @PublishedApi
    internal fun navigator(): NavHostController {
        return get<EMAppNavController>(EMAppNavController::class.java).getAppNavHostController()
    }

    @Composable
    inline fun <reified T : Parcelable> NavBackStackEntry.getArgument(
        key: String,
        route: String
    ): T? {
        val parentEntry = remember(this) { navigator().getBackStackEntry(route) }
        return parentEntry.arguments?.getParcelable(key)
    }

    @Composable
    fun NavBackStackEntry.getArgument2(
        key: String,
        route: String
    ): String? {
        val parentEntry = remember(this) { navigator().getBackStackEntry(route) }
        return parentEntry.arguments?.getString(key)
    }
}