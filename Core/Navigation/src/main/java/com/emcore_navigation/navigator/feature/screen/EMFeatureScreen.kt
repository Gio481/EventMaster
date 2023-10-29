package com.emcore_navigation.navigator.feature.screen

import androidx.navigation.NavGraphBuilder
import com.emcore_navigation.navigator.base.screen.EMBaseScreen
import com.emcore_navigation.navigator.feature.extension.addFeature

abstract class EMFeatureScreen : EMBaseScreen {

    fun initFeature(appGraphBuilder: NavGraphBuilder) {
        appGraphBuilder.addFeature(config) {
            addArgumentIfNeeds()
            config.flowInitializer.invoke(this)
        }
    }

    private val config get() = config() as EMFeatureScreenConfig

    private fun NavGraphBuilder.addArgumentIfNeeds() {
        config.argument?.let { (key, type) ->
            argument(key) { this.type = type }
        }
    }
}
