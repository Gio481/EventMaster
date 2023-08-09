package com.eventmaster.di.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilder
import com.eventmaster.home.impl.navigation.EMHomeInitializer
import com.eventmaster.splash.impl.navigation.EMSplashInitializer

class EMFeaturesInitializerImpl(
    private val appGraphBuilder: EMAppGraphBuilder
) : EMFeaturesInitializer {

    override fun init() {
        val splash = EMSplashInitializer.features()
        val home = EMHomeInitializer.features()
        initFeatures(splash, home)
    }

    private fun initFeatures(vararg list: List<EMFeatureScreen>) {
        list.forEach { initEachFeature(it) }
    }

    private fun initEachFeature(feature: List<EMFeatureScreen>) {
        feature.forEach {
            it.featureGraphBuilder().also { graph ->
                it.initFeature(graph)
                appGraphBuilder.getAppNavGraphBuilder().destination(graph)
            }
        }
    }
}