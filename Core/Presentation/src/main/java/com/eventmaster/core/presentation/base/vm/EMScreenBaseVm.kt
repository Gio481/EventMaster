package com.eventmaster.core.presentation.base.vm

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.emcore_navigation.navigator.base.screen.EMBaseScreen
import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.emcore_navigation.navigator.flow.EMFlowNavigator
import org.koin.java.KoinJavaComponent.inject

abstract class EMScreenBaseVm : ViewModel() {

    private val featureNavigator by inject<EMFeatureNavigator>(EMFeatureNavigator::class.java)
    private val flowNavigator by inject<EMFlowNavigator>(EMFlowNavigator::class.java)

    @CallSuper
    open fun onCreate() {
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