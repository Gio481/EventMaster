package com.eventmaster.core.presentation.base.vm

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emcore_navigation.navigator.base.screen.EMBaseScreen
import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.emcore_navigation.navigator.flow.EMFlowNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.java.KoinJavaComponent.inject

abstract class EMScreenBaseVm : ViewModel() {

    private val featureNavigator by inject<EMFeatureNavigator>(EMFeatureNavigator::class.java)
    private val flowNavigator by inject<EMFlowNavigator>(EMFlowNavigator::class.java)

    @CallSuper
    open fun onCreate() {
    }

    protected fun <T> EMObservable<T>.liveDataPost(value: T) {
        liveData.post(value)
        liveDataWithValue.post(value)
    }

    protected fun <T> EMObservable<T>.statePost(value: T) {
        stateFlow.post(value)
        stateFlowWithValue.post(value)
    }


    private fun <T> StateFlow<T>.post(value: T) {
        (this as MutableStateFlow).value = value
    }

    private fun <T> LiveData<T>.post(value: T) {
        (this as MutableLiveData).value = value
    }

    @CallSuper
    open suspend fun suspendOnCreate() {
        collectTriggeredEvents()
    }

    protected open suspend fun collectTriggeredEvents() {}

    protected fun nextScreen(screen: EMBaseScreen) {
        flowNavigator.nextScreen(screen)
    }

    fun finishFeatureWithResult(result: Bundle = Bundle.EMPTY) {
        featureNavigator.finishFeatureWithResult(result)
    }

    fun finishFeature() {
        featureNavigator.finishFeature()
    }

    fun navigateBack() {
        flowNavigator.navigateBack()
    }
}