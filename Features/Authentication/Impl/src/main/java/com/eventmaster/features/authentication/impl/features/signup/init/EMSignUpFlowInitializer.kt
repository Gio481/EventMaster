package com.eventmaster.features.authentication.impl.features.signup.init

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.flow.initializer.EMFlowInitializer
import com.eventmaster.features.authentication.impl.features.signup.screens.EMSignUpScreens

object EMSignUpFlowInitializer : EMFlowInitializer {
    override fun init(navGraphBuilder: NavGraphBuilder) {
        EMSignUpScreens.Email.screen(navGraphBuilder)
        EMSignUpScreens.Password.screen(navGraphBuilder)
    }
}