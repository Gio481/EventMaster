package com.eventmaster.features.group.impl.presentation.groups.multi_groups.di

import com.eventmaster.features.group.impl.presentation.groups.multi_groups.vm.EMMultiGroupsVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val multiGroupModule = module {
    viewModel {
        EMMultiGroupsVm()
    }
}