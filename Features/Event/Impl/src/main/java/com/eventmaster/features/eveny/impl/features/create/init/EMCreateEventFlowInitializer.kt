package com.eventmaster.features.eveny.impl.features.create.init

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.initializer.EMFlowInitializer
import com.eventmaster.features.eveny.impl.features.create.screens.EMCreateEventFlowScreen

object EMCreateEventFlowInitializer : EMFlowInitializer {
    override fun init(navGraphBuilder: NavGraphBuilder) {
        EMCreateEventFlowScreen.Cover.screen(navGraphBuilder)
        EMCreateEventFlowScreen.Date.screen(navGraphBuilder)
        EMCreateEventFlowScreen.FriendsList.screen(navGraphBuilder)
        EMCreateEventFlowScreen.CreateTasks.screen(navGraphBuilder)
    }
}