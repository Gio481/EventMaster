package com.emcore_navigation.extensions

import android.os.Parcelable
import com.emcore_navigation.nav_type.EMCustomNavType


inline fun <reified T : Parcelable> Any.navType(): EMCustomNavType<T> {
    return EMCustomNavType(T::class.java)
}