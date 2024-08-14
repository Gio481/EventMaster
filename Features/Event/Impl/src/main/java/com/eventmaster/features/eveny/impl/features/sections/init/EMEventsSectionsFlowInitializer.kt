package com.eventmaster.features.eveny.impl.features.sections.init

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.initializer.EMFlowInitializer
import com.eventmaster.features.eveny.impl.features.sections.screens.EMEventsSectionsFlowScreen

object EMEventsSectionsFlowInitializer : EMFlowInitializer {
    override fun init(navGraphBuilder: NavGraphBuilder) {
        EMEventsSectionsFlowScreen.Main.screen(navGraphBuilder)
        EMEventsSectionsFlowScreen.Details.screen(navGraphBuilder)
    }
}