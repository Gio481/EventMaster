package com.eventmaster.features.home.impl.presentation.widgets.states.model

import androidx.compose.ui.graphics.Color

sealed class EMHomeWidgetState {
    data class Empty(
        val background: Color,
        val title: String,
        val onClickAction: () -> Unit
    ) : EMHomeWidgetState()

    object Loading : EMHomeWidgetState()

    object Failure : EMHomeWidgetState()

    object Success : EMHomeWidgetState()
}