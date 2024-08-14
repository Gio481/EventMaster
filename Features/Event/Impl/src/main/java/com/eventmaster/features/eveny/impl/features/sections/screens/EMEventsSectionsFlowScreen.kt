package com.eventmaster.features.eveny.impl.features.sections.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.features.eveny.impl.presentation.create.date.ui.EMCreateEventDateComposable
import com.eventmaster.features.eveny.impl.presentation.sections.main.ui.EMEventsSectionsMain

sealed class EMEventsSectionsFlowScreen : EMFlowScreen() {
    object Main : EMEventsSectionsFlowScreen() {
        override fun config() = EMFlowScreenConfig(
            route = "events_sections",
            content = { EMEventsSectionsMain() },
        )

    }

    object Details : EMEventsSectionsFlowScreen() {
        override fun config() = EMFlowScreenConfig(
            route = "event_section_details",
            content = { EMCreateEventDateComposable() },
        )
    }
}