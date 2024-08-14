package com.eventmaster.core.presentation.base.vm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EMObservable<T>(private val initialValue: T? = null) {

    internal val stateFlow: StateFlow<T?> = MutableStateFlow(initialValue)
    internal val stateFlowWithValue: StateFlow<T> = MutableStateFlow(initialValue!!)

    internal val liveData: LiveData<T> = MutableLiveData()
    internal val liveDataWithValue: LiveData<T> = MutableLiveData(initialValue!!)

    @Composable
    fun collect(): T? {
        return stateFlow.collectAsState().value
    }

    @Composable
    fun collectWithInitialValue(): T {
        return stateFlowWithValue.collectAsStateWithLifecycle().value
    }

    @Composable
    fun observe(): T? {
        return liveData.observeAsState().value
    }

    @Composable
    fun observeWithInitialValue(): T {
        return liveDataWithValue.observeAsState(initialValue!!).value
    }
}