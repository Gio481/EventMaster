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
    open fun onCreate() {}

    @CallSuper
    open suspend fun suspendOnCreate() {}

    protected fun nextScreen(screen: EMBaseScreen) {
        flowNavigator.nextScreen(screen)
    }

    protected fun finishFeatureWithResult(feature: EMBaseScreen, result: Bundle = Bundle.EMPTY) {
        featureNavigator.finishFeatureWithResult(feature, result)
    }

    protected fun finishFeature(feature: EMBaseScreen) {
        featureNavigator.finishFeature(feature)
    }
}