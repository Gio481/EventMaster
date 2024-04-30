package com.eventmaster.core.presentation.extensions

fun String.isSpannable(): Boolean {
    return contains("[") && contains("]")
}