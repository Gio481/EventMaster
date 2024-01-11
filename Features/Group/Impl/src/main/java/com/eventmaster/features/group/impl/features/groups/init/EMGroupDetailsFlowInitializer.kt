package com.eventmaster.features.group.impl.features.groups.init

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.initializer.EMFlowInitializer
import com.eventmaster.features.group.impl.features.groups.screens.EMGroupsFlowScreen

object EMGroupDetailsFlowInitializer : EMFlowInitializer {
    override fun init(navGraphBuilder: NavGraphBuilder) {
        EMGroupsFlowScreen.MultiGroups.screen(navGraphBuilder)
        EMGroupsFlowScreen.Details.screen(navGraphBuilder)
    }
}