package com.eventmaster.features.eveny.impl.presentation.create.date.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.features.eveny.impl.features.create.screens.EMCreateEventFlowScreen

class EMCreateEventDateVm : EMScreenBaseVm() {
    fun nextStep(){
        nextScreen(EMCreateEventFlowScreen.FriendsList)
    }
}