package com.emcore_navigation.navigator.feature

import com.emcore_navigation.navigator.feature.screen.EMFeatureScreen

interface EMFeatureInitializer {
    fun features(): List<EMFeatureScreen>
}