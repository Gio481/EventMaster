package com.eventmaster.features.authentication.impl.presentation.auth.main.di

import com.eventmaster.features.authentication.impl.presentation.auth.main.vm.EMAuthVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel {
        EMAuthVm(navigator = get(), test = get())
    }
}