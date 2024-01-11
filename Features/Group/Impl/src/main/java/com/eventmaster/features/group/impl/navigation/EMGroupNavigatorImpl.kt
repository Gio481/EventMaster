package com.eventmaster.features.group.impl.navigation

import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.eventmaster.features.group.api.navigation.EMGroupNavigator

class EMGroupNavigatorImpl(private val featureNavigator: EMFeatureNavigator) : EMGroupNavigator {

    override fun navigateToCreateGroup() {
        featureNavigator.navigateTo(EMGroupFeatureScreens.Create)
    }

    override fun navigateToGroups() {
        featureNavigator.navigateTo(EMGroupFeatureScreens.Details)
    }
}