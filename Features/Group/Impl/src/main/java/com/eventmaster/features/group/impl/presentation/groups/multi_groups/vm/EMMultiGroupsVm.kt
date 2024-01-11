package com.eventmaster.features.group.impl.presentation.groups.multi_groups.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.features.group.impl.features.groups.screens.EMGroupsFlowScreen

class EMMultiGroupsVm:EMScreenBaseVm() {
    fun navigateToDetails(){
        nextScreen(EMGroupsFlowScreen.Details)
    }
}