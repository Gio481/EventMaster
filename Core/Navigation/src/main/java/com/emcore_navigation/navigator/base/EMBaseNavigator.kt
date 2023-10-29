package com.emcore_navigation.navigator.base

import android.os.Bundle
import androidx.navigation.NavOptions
import com.emcore_navigation.navigator.base.screen.EMBaseScreen
import com.emcore_navigation.navigator.base.screen.EMBaseScreenConfig
import com.emcore_navigation.navigator.base.type.EMNavigatorType
import com.emcore_navigation.navigator.nav_controller.EMAppNavController
import com.google.gson.Gson


abstract class EMBaseNavigator(
    private val navController: EMAppNavController
) {

    abstract fun navigatorType(): EMNavigatorType

    protected val navHostController by lazy { navController.getAppNavHostController() }

    /**
     * Set the navigation result callback on calling screen.
     *
     * @param callback The navigation result callback.
     */
    private fun setNavResultCallback(key: String, callback: (Bundle) -> Unit) {
        navHostController.currentBackStackEntry?.savedStateHandle?.set(
            key = key,
            value = callback
        )
    }

    /**
     *  Get the navigation result callback on called screen.
     *
     * @return The navigation result callback if the previous backstack entry exists
     */
    protected fun getNavResultCallback(key: String): ((Bundle) -> Unit)? {
        return navHostController.previousBackStackEntry?.savedStateHandle?.remove(key)
    }

    /**
     * Navigate to a route in the current NavGraph. If an invalid route is given, an
     * [IllegalArgumentException] will be thrown.
     *
     * @param screen route for the destination
     * @param navResultCallback the navigation result callback
     * @param navOptions special options for this navigation operation
     *
     * @throws IllegalArgumentException if the given route is invalid
     */
    fun navigateTo(
        screen: EMBaseScreen,
        navOptions: NavOptions? = null,
        navResultCallback: ((Bundle) -> Unit)? = null,
    ) {

        navResultCallback?.let { setNavResultCallback(getKey(screen), it) }
        navHostController.navigate(screen.config().route, navOptions)
    }

    fun <T> navigateTo(
        screen: EMBaseScreen,
        data: T,
        navOptions: NavOptions? = null,
        navResultCallback: ((Bundle) -> Unit)? = null,
    ) {
        navResultCallback?.let { setNavResultCallback(getKey(screen), it) }
        navHostController.navigate(routeWithArgument(screen.config(), data), navOptions)
    }

    protected fun getKey(screen: EMBaseScreen): String {
        return screen.config().argument?.key
            ?: throw Exception("you should set up the EMScreenConfig.Argument")
    }

    private fun <T> routeWithArgument(config: EMBaseScreenConfig, data: T): String {
        val regex = "\\{([^}]*)\\}".toRegex()
        return config.route.replace(regex) { Gson().toJson(data) }
    }
}