package com.eventmaster.features.eveny.impl.navigation

import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.eventmaster.features.event.api.navigation.EMEventNavigator

class EMEventNavigatorImpl(
    private val featureNavigator: EMFeatureNavigator
) : EMEventNavigator {

    override fun navigateToCreateEvent() {
        featureNavigator.navigateTo(EMEventFeatureScreens.Create)
    }

    override fun navigateToEventsSections() {
        featureNavigator.navigateTo(EMEventFeatureScreens.Sections)
    }

    override fun navigateToEventDetails() {
        TODO("Not yet implemented")
    }

}