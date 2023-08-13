package com.eventmaster.splash.impl.presentation.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eventmaster.home.api.navigation.EMHomeNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EMSplashVm(
    private val homeNavigator: EMHomeNavigator
) : ViewModel() {

    fun navigate() {
        viewModelScope.launch {
            delay(500)
            homeNavigator.navigateToHome()
        }
    }
}