package com.emcore_navigation.navigator.flow

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.base.EMBaseScreen

abstract class EMFlowScreen : EMBaseScreen {

    abstract fun screen(navGraphBuilder: NavGraphBuilder)
}