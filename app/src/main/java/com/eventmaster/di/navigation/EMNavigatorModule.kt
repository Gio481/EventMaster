package com.eventmaster.di.navigation

import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.emcore_navigation.navigator.feature.EMFeatureNavigatorImpl
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilder
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilderImpl
import com.emcore_navigation.navigator.nav_controller.EMAppNavController
import com.emcore_navigation.navigator.nav_controller.EMAppNavControllerImpl
import com.eventmaster.features.splash.api.navigation.EMSplashNavigator
import com.eventmaster.features.splash.impl.navigation.EMSplashNavigatorImpl
import org.koin.dsl.module

val navigatorModule = module {
    single<EMAppGraphBuilder> { EMAppGraphBuilderImpl(navHostController = get()) }
    single<EMAppNavController> { EMAppNavControllerImpl() }
    single<EMFeaturesInitializer> { EMFeaturesInitializerImpl(appGraphBuilder = get()) }
    single<EMFeatureNavigator> { EMFeatureNavigatorImpl(navController = get()) }
    single<EMSplashNavigator> {
        EMSplashNavigatorImpl(
            featureNavigator = get()
        )
    }
}