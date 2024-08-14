package com.eventmaster.features.eveny.impl.features.create.screens

import com.emcore_navigation.navigator.flow.screen.EMFlowScreen
import com.emcore_navigation.navigator.flow.screen.EMFlowScreenConfig
import com.eventmaster.features.eveny.impl.presentation.create.add_task.ui.EMCreateEventAddTask
import com.eventmaster.features.eveny.impl.presentation.create.cover.ui.EMCreateEventCoverComposable
import com.eventmaster.features.eveny.impl.presentation.create.date.ui.EMCreateEventDateComposable
import com.eventmaster.features.eveny.impl.presentation.create.friends_list.ui.EMCreateEventFriendsListComposable

sealed class EMCreateEventFlowScreen : EMFlowScreen() {

    object Cover : EMCreateEventFlowScreen() {
        override fun config() = EMFlowScreenConfig(
            route = "cover",
            content = { EMCreateEventCoverComposable() }
        )
    }

    object Date : EMCreateEventFlowScreen() {
        override fun config() = EMFlowScreenConfig(
            route = "date",
            content = { EMCreateEventDateComposable() },
            showFlowTransitionAnimation = true
        )
    }

    object FriendsList : EMCreateEventFlowScreen() {
        override fun config() = EMFlowScreenConfig(
            route = "friendsList",
            content = { EMCreateEventFriendsListComposable() },
            showFlowTransitionAnimation = true
        )
    }

    object CreateTasks : EMCreateEventFlowScreen() {
        override fun config() = EMFlowScreenConfig(
            route = "createTasks",
            content = { EMCreateEventAddTask()},
            showFlowTransitionAnimation = true
        )
    }
}