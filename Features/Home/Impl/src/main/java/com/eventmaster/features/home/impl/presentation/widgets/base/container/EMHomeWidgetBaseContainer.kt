package com.eventmaster.features.home.impl.presentation.widgets.base.container

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import com.eventmaster.features.home.impl.presentation.widgets.states.empty.EMHomeWidgetEmptyState
import com.eventmaster.features.home.impl.presentation.widgets.states.model.EMHomeWidgetState
import org.koin.java.KoinJavaComponent.get

abstract class EMHomeWidgetBaseContainer : EMHomeWidgetApi {

    @Composable
    abstract fun DrawSuccessContent()

    private var widgetState = mutableStateOf<EMHomeWidgetState?>(null)

    protected val context = LocalContext.provides(get(Context::class.java)).value

    @Composable
    override fun loadContent() {
        when (widgetState.value) {
            is EMHomeWidgetState.Empty -> EMHomeWidgetEmptyState(widgetState.value as EMHomeWidgetState.Empty)
            is EMHomeWidgetState.Success -> DrawSuccessContent()
            else -> Unit
        }
    }


    fun changeState(state: EMHomeWidgetState) {
        this.widgetState.value = state
    }

    fun loadingState() {}

    fun failureState() {}

    fun successState() {
        this.widgetState.value = EMHomeWidgetState.Success
    }
}