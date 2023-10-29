package com.eventmaster.features.authentication.impl.presentation.signup.password.di

import com.eventmaster.features.authentication.impl.presentation.signup.password.vm.EMSignUpPasswordVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val passwordModule = module {
    viewModel {
        EMSignUpPasswordVm()
    }
}