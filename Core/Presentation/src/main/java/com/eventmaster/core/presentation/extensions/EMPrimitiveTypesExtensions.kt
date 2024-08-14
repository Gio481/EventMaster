package com.eventmaster.core.presentation.extensions

fun String.isSpannable(): Boolean {
    return contains("[") && contains("]")
}

fun String.isEmail(): Boolean {
    if (isNullOrEmpty()) return false

    val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()

    if (emailPattern.matches(this)) {
        val atIndex = indexOf('@')
        val dotIndex = indexOf('.', atIndex)
        if (dotIndex == -1) return false

        val domain = substring(atIndex + 1, length)
        return domain.length >= 2
    }

    return false
}

const val EMPTY_TEXT = ""