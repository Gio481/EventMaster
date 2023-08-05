package com.eventmaster.di.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilder
import com.eventmaster.splash.impl.navigation.EMSplashInitializer

class EMFeaturesInitializerImpl(
    private val appGraphBuilder: EMAppGraphBuilder
) : EMFeaturesInitializer {

    override fun init() {
        val splash = EMSplashInitializer.features()
        initEachFeature(splash)
    }

    private fun initEachFeature(list: List<EMFeatureScreen>) {
        list.forEach {
            it.featureGraphBuilder().also { graph ->
                it.initFeature(graph)
                appGraphBuilder.getAppNavGraphBuilder().destination(graph)
            }
        }
    }
}