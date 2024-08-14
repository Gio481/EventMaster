package com.eventmaster.features.eveny.impl.presentation.sections.main.di

import com.eventmaster.features.eveny.impl.presentation.sections.main.vm.EMEventsSectionsMainVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val eventsSectionsModule = module {
    viewModel {
        EMEventsSectionsMainVm()
    }
}