package com.eventmaster.core.presentation.extensions

fun <T> List<T>.getIndexOrNull(element: T): Int? {
    val index = indexOf(element)
    return if (index == -1) null else index
}