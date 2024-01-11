package com.eventmaster.di.navigation

import com.emcore_navigation.navigator.feature.EMFeatureNavigator
import com.emcore_navigation.navigator.flow.EMFlowNavigator
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilder
import com.emcore_navigation.navigator.graph_builder.EMAppGraphBuilderImpl
import com.emcore_navigation.navigator.nav_controller.EMAppNavController
import com.emcore_navigation.navigator.nav_controller.EMAppNavControllerImpl
import com.eventmaster.features.authentication.api.navigation.EMAuthenticationNavigator
import com.eventmaster.features.authentication.impl.navigation.EMAuthenticationNavigatorImpl
import com.eventmaster.features.event.api.navigation.EMEventNavigator
import com.eventmaster.features.eveny.impl.navigation.EMEventNavigatorImpl
import com.eventmaster.features.group.api.navigation.EMGroupNavigator
import com.eventmaster.features.group.impl.navigation.EMGroupNavigatorImpl
import com.eventmaster.features.home.api.navigation.EMHomeNavigator
import com.eventmaster.features.home.impl.navigation.EMHomeNavigatorImpl
import com.eventmaster.features.splash.api.navigation.EMSplashNavigator
import com.eventmaster.features.splash.impl.navigation.EMSplashNavigatorImpl
import org.koin.dsl.module

val navigatorModule = module {
    single<EMAppGraphBuilder> { EMAppGraphBuilderImpl(navHostController = get()) }
    single<EMAppNavController> { EMAppNavControllerImpl() }
    single<EMFeaturesInitializer> { EMFeaturesInitializerImpl(appGraphBuilder = get()) }
    single { EMFeatureNavigator(navController = get()) }
    single { EMFlowNavigator(navController = get()) }
    single<EMSplashNavigator> { EMSplashNavigatorImpl(featureNavigator = get()) }
    single<EMAuthenticationNavigator> { EMAuthenticationNavigatorImpl(featureNavigator = get()) }
    single<EMEventNavigator> { EMEventNavigatorImpl(featureNavigator = get()) }
    single<EMHomeNavigator> { EMHomeNavigatorImpl(featureNavigator = get()) }
    single<EMGroupNavigator> { EMGroupNavigatorImpl(featureNavigator = get()) }
}