package com.eventmaster.features.eveny.impl.presentation.create.friends_list.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.features.eveny.impl.features.create.screens.EMCreateEventFlowScreen

class EMCreateEventFriendsListVm : EMScreenBaseVm() {
    fun onNextAction(){
        nextScreen(EMCreateEventFlowScreen.CreateTasks)
    }
}