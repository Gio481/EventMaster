package com.eventmaster.features.authentication.impl.presentation.signup.profile.di

import com.eventmaster.features.authentication.impl.presentation.signup.profile.vm.EMSignUpProfileVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel {
        EMSignUpProfileVm()
    }
}