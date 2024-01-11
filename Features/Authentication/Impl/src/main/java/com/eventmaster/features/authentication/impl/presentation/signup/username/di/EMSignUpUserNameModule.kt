package com.eventmaster.features.authentication.impl.presentation.signup.username.di

import com.eventmaster.features.authentication.impl.presentation.signup.username.vm.EMSignUpUserNameVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userNameModule = module {
    viewModel {
        EMSignUpUserNameVm()
    }
}