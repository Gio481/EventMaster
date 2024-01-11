package com.eventmaster.features.group.impl.presentation.groups.details.di

import com.eventmaster.features.group.impl.presentation.groups.details.vm.EMGroupDetailsVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val groupDetailsModule = module {
    viewModel {
        EMGroupDetailsVm()
    }
}