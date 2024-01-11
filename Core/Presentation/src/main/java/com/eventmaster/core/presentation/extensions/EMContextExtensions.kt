package com.eventmaster.core.presentation.extensions

import android.content.Context

fun Context.readMotionScene(raw: Int): String {
    return resources.openRawResource(raw).readBytes().decodeToString()
}