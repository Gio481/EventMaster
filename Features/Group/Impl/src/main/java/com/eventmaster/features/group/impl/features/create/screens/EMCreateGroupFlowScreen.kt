package com.eventmaster.features.group.impl.features.create.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.features.group.impl.presentation.create.cover.ui.EMCreateGroupCoverComposable

sealed class EMCreateGroupFlowScreen : EMFlowScreen() {

    object Cover : EMCreateGroupFlowScreen() {
        override fun config() = EMFlowScreenConfig(
            route = "groupCover",
            content = { EMCreateGroupCoverComposable() }
        )
    }
}