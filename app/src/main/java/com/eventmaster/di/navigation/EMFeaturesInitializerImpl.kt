package com.eventmaster.di.navigation

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilder
import com.eventmaster.features.authentication.impl.navigation.EMAuthenticationInitializer
import com.eventmaster.features.eveny.impl.navigation.EMEventInitializer
import com.eventmaster.features.group.impl.navigation.EMGroupInitializer
import com.eventmaster.features.home.impl.navigation.EMHomeInitializer
import com.eventmaster.features.splash.impl.navigation.EMSplashInitializer

class EMFeaturesInitializerImpl(
    private val appGraphBuilder: EMAppGraphBuilder
) : EMFeaturesInitializer {

    override fun init() {
        val splash = EMSplashInitializer.features()
        val authentication = EMAuthenticationInitializer.features()
        val event = EMEventInitializer.features()
        val home = EMHomeInitializer.features()
        val group = EMGroupInitializer.features()
        initFeatures(splash, authentication, event, home, group)
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