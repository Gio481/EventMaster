package com.eventmaster.features.home.impl.presentation.home.di

import com.eventmaster.features.home.impl.presentation.home.vm.EMHomeVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        EMHomeVm()
    }
}