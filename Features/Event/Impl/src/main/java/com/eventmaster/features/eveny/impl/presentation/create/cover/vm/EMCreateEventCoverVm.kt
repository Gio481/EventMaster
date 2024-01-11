package com.eventmaster.features.eveny.impl.presentation.create.cover.vm

import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm
import com.eventmaster.features.eveny.impl.features.create.screens.EMCreateEventFlowScreen

class EMCreateEventCoverVm : EMScreenBaseVm() {
    fun onNextAction() {
        nextScreen(EMCreateEventFlowScreen.Date)
    }
}