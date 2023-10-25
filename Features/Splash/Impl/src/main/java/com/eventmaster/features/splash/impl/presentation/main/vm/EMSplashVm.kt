package com.eventmaster.features.splash.impl.presentation.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EMSplashVm(
) : ViewModel() {

    fun navigate() {
        viewModelScope.launch {
            delay(500)
        }
    }
}