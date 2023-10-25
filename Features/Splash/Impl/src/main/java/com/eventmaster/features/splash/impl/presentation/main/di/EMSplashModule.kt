package com.eventmaster.features.splash.impl.presentation.main.di

import com.eventmaster.features.splash.impl.presentation.main.vm.EMSplashVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel {
        EMSplashVm()
    }
}