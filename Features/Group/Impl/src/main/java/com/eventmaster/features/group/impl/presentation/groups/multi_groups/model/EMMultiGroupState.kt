package com.eventmaster.features.group.impl.presentation.groups.multi_groups.model

sealed class EMMultiGroupState {
    object Empty : EMMultiGroupState()
    object Error : EMMultiGroupState()
    object Loading : EMMultiGroupState()
    object Success : EMMultiGroupState()
}