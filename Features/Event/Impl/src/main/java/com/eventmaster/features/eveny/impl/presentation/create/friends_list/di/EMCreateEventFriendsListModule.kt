package com.eventmaster.features.eveny.impl.presentation.create.friends_list.di

import com.eventmaster.features.eveny.impl.presentation.create.friends_list.vm.EMCreateEventFriendsListVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val friendsListModule = module {
    viewModel {
        EMCreateEventFriendsListVm()
    }
}