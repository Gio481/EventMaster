package com.eventmaster.features.authentication.impl.presentation.signup.success.di

import com.eventmaster.features.authentication.impl.presentation.signup.success.vm.EMSignUpSuccessVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signUpSuccessModule = module {
    viewModel {
        EMSignUpSuccessVm(get())
    }
}