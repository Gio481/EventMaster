package com.emcore_navigation.navigator.base

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

typealias NavResultCallback = (Bundle) -> Unit

class EMBaseNavigator {

    // A SavedStateHandle key is used to set/get NavResultCallback<T>
    private val NavResultCallbackKey = "NavResultCallbackKey"

    /**
     * Set the navigation result callback on calling screen.
     *
     * @param callback The navigation result callback.
     */
    fun NavController.setNavResultCallback(callback: NavResultCallback) {
        currentBackStackEntry?.savedStateHandle?.set(NavResultCallbackKey, callback)
    }

    /**
     *  Get the navigation result callback on called screen.
     *
     * @return The navigation result callback if the previous backstack entry exists
     */
    fun <T> NavController.getNavResultCallback(): NavResultCallback? {
        return previousBackStackEntry?.savedStateHandle?.remove(NavResultCallbackKey)
    }

    /**
     *  Attempts to pop the controller's back stack and returns the result.
     *
     * @param result the navigation result
     */
    fun <T> NavController.popBackStackWithResult(result: Bundle) {
        getNavResultCallback<T>()?.invoke(result)
        popBackStack()
    }

    /**
     * Navigate to a route in the current NavGraph. If an invalid route is given, an
     * [IllegalArgumentException] will be thrown.
     *
     * @param route route for the destination
     * @param navResultCallback the navigation result callback
     * @param navOptions special options for this navigation operation
     * @param navigatorExtras extras to pass to the [Navigator]
     *
     * @throws IllegalArgumentException if the given route is invalid
     */
    fun NavController.navigateForResult(
        route: String,
        navResultCallback: NavResultCallback,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        setNavResultCallback(navResultCallback)
        navigate(route, navOptions, navigatorExtras)
    }

    /**
     * Navigate to a route in the current NavGraph. If an invalid route is given, an
     * [IllegalArgumentException] will be thrown.
     *
     * @param route route for the destination
     * @param navResultCallback the navigation result callback
     * @param builder DSL for constructing a new [NavOptions]
     *
     * @throws IllegalArgumentException if the given route is invalid
     */
    fun NavController.navigateForResult(
        route: String,
        navResultCallback: NavResultCallback,
    ) {
        setNavResultCallback(navResultCallback)
        navigate(route)
    }
}