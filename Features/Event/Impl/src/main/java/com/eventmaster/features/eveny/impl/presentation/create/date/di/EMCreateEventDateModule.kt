package com.eventmaster.features.eveny.impl.presentation.create.date.di

import com.eventmaster.features.eveny.impl.presentation.create.cover.vm.EMCreateEventCoverVm
import com.eventmaster.features.eveny.impl.presentation.create.date.vm.EMCreateEventDateVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dateModule = module {
    viewModel {
        EMCreateEventDateVm()
    }
}