package com.eventmaster.features.authentication.impl.presentation.signup.mail.di

import com.eventmaster.features.authentication.impl.presentation.signup.mail.vm.EMSignUpMailVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mailModule = module {
    viewModel {
        EMSignUpMailVm()
    }
}