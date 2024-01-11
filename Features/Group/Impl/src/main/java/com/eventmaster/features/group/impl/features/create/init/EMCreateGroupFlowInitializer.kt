package com.eventmaster.features.group.impl.features.create.init

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.initializer.EMFlowInitializer
import com.eventmaster.features.group.impl.features.create.screens.EMCreateGroupFlowScreen

internal object EMCreateGroupFlowInitializer : EMFlowInitializer {
    override fun init(navGraphBuilder: NavGraphBuilder) {
        EMCreateGroupFlowScreen.Cover.screen(navGraphBuilder)
    }
}