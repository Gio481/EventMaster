package com.emcore_navigation.navigator.feature

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen

interface EMFeatureNavigator {

    fun navigateTo(feature: EMFeatureScreen)

}