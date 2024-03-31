package com.eventmaster.features.eveny.impl.presentation.create.add_task.di

import com.eventmaster.features.eveny.impl.presentation.create.add_task.vm.EMCreateEventAddTaskVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addTaskModule = module {
    viewModel {
        EMCreateEventAddTaskVm()
    }
}