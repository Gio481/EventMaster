package com.eventmaster.features.authentication.impl.presentation.login.main.di

import com.eventmaster.features.authentication.impl.presentation.login.main.vm.EMLogInVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val logInModule = module {
    viewModel {
        EMLogInVm()
    }
}