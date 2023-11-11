package com.eventmaster.features.authentication.impl.features.auth.init

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.initializer.EMFlowInitializer
import com.eventmaster.features.authentication.impl.features.auth.screens.EMAuthFlowScreens
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
object EMAuthFlowInitializer : EMFlowInitializer {
    override fun init(navGraphBuilder: NavGraphBuilder) {
        EMAuthFlowScreens.Main.screen(navGraphBuilder)
    }
}