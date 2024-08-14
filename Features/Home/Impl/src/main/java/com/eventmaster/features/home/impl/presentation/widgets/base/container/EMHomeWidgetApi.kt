package com.eventmaster.features.home.impl.presentation.widgets.base.container

import androidx.compose.runtime.Composable

sealed interface EMHomeWidgetApi {


    fun update()

    @Composable
    fun loadContent()
}