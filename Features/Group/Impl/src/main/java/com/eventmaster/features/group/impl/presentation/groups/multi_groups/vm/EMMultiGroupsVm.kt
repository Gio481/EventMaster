package com.eventmaster.features.group.impl.presentation.groups.multi_groups.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.core.presentation.base.vm.stateWithValue
import com.eventmaster.features.group.impl.features.groups.screens.EMGroupsFlowScreen
import com.eventmaster.features.group.impl.presentation.groups.multi_groups.model.EMMultiGroupState

class EMMultiGroupsVm : EMScreenBaseVm() {

    val groupState = stateWithValue<EMMultiGroupState>(EMMultiGroupState.Loading)

    fun navigateToDetails() {
        nextScreen(EMGroupsFlowScreen.Details)
    }

    override fun onCreate() {
        super.onCreate()
        groupState.statePost(EMMultiGroupState.Loading)
    }

//    private fun changeState(state: EMMultiGroupState) {
//        groupState.statePost(state)
//    }
}