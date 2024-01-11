package com.eventmaster.features.group.impl.features.groups.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.features.group.impl.presentation.groups.details.ui.EMGroupDetailsComposable
import com.eventmaster.features.group.impl.presentation.groups.multi_groups.ui.EMMultiGroupsComposable

sealed class EMGroupsFlowScreen : EMFlowScreen() {

    object MultiGroups : EMGroupsFlowScreen() {
        override fun config() = EMFlowScreenConfig(
            route = "multiGroup",
            content = { EMMultiGroupsComposable() }
        )
    }

    object Details : EMGroupsFlowScreen() {
        override fun config() = EMFlowScreenConfig(
            route = "groupDetails",
            showAnimation = true,
            content = { EMGroupDetailsComposable() }
        )
    }
}