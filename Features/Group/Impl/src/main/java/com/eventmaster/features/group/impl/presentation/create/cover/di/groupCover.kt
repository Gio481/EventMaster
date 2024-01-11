package com.eventmaster.features.group.impl.presentation.create.cover.di

import com.eventmaster.features.group.impl.presentation.create.cover.vm.EMCreateGroupCoverVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val groupCoverModule = module {
    viewModel {
        EMCreateGroupCoverVm()
    }
}