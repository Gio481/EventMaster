package com.eventmaster.di.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilder
import com.eventmaster.features.authentication.impl.navigation.EMAuthenticationInitializer
import com.eventmaster.features.splash.impl.navigation.EMSplashInitializer

class EMFeaturesInitializerImpl(
    private val appGraphBuilder: EMAppGraphBuilder
) : EMFeaturesInitializer {

    override fun init() {
        val splash = EMSplashInitializer.features()
        val authentication = EMAuthenticationInitializer.features()
        initFeatures(splash, authentication)
    }

    private fun initFeatures(vararg list: List<EMFeatureScreen>) {
        list.forEach { addEachFeature(it) }
    }

    private fun addEachFeature(feature: List<EMFeatureScreen>) {
        feature.forEach {
            it.initFeature(appGraphBuilder.getAppNavGraphBuilder())
        }
    }
}