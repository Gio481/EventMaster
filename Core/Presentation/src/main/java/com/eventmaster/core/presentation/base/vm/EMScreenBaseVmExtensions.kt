package com.eventmaster.core.presentation.base.vm

fun <T> EMScreenBaseVm.state() = EMObservable<T>()
fun <T> EMScreenBaseVm.stateWithValue(initialValue: T) = EMObservable(initialValue)

fun <T> EMScreenBaseVm.liveData() = EMObservable<T>()
fun <T> EMScreenBaseVm.liveDataWithValue(initialValue: T) = EMObservable(initialValue)