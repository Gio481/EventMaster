package com.eventmaster.features.home.impl.presentation.home.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.features.event.api.navigation.EMEventNavigator
import com.eventmaster.features.group.api.navigation.EMGroupNavigator

class EMHomeVm(
    private val groupNavigator: EMGroupNavigator,
    private val eventsNavigator: EMEventNavigator
) : EMScreenBaseVm() {

    fun navigateToCreateEvent() {
        eventsNavigator.navigateToCreateEvent()
    }

    fun navigateToCreateGroup() {
        groupNavigator.navigateToCreateGroup()
    }

    fun navigateToGroups(){
        groupNavigator.navigateToGroups()
    }

}