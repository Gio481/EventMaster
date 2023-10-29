package com.emcore_navigation.navigator.flow.initializer

import androidx.navigation.NavGraphBuilder

interface EMFlowInitializer {
    fun init(navGraphBuilder: NavGraphBuilder)
}