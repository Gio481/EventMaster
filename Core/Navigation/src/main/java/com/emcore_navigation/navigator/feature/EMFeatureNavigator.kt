package com.emcore_navigation.navigator.feature

import android.os.Bundle
import android.util.Log.d
import com.emcore_navigation.navigator.base.EMBaseNavigator
import com.emcore_navigation.navigator.base.type.EMNavigatorType
import com.emcore_navigation.navigator.nav_controller.EMAppNavController

class EMFeatureNavigator(navController: EMAppNavController) : EMBaseNavigator(navController) {

    fun finishFeatureWithResult(result: Bundle = Bundle.EMPTY) {
        getNavResultCallback(getKey(featureScreenList.last()))?.invoke(result)
        navHostController.popBackStack(featureScreenList.last().route, true)
    }

    fun finishFeature() {
        navHostController.popBackStack(featureScreenList.last().route, true)
        featureScreenList.removeLast()
    }
}