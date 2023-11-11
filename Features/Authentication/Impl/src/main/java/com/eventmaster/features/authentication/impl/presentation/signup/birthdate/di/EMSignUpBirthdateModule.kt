package com.eventmaster.features.authentication.impl.presentation.signup.birthdate.di

import com.eventmaster.features.authentication.impl.presentation.signup.birthdate.vm.EMSignUpBirthdateVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val birthdateModule = module {
    viewModel {
        EMSignUpBirthdateVm()
    }
}