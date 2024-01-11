package com.eventmaster.features.eveny.impl.presentation.create.cover.di

import com.eventmaster.features.eveny.impl.presentation.create.cover.vm.EMCreateEventCoverVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coverModule = module {
    viewModel {
        EMCreateEventCoverVm()
    }
}