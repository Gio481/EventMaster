package com.emcore_navigation.extensions

import android.os.Parcelable
import com.emcore_navigation.nav_type.EMCustomNavType

/**
 * [customNavType] Creates [EMCustomNavType] which helps us to set our
 * custom navigationType to pass the argument as an Object during the navigation
 *
 * @param T The type of Parcelable to be used as the argument in [EMCustomNavType].
 * @return [EMCustomNavType] Custom NavType
 */
inline fun <reified T : Parcelable> Any.customNavType(): EMCustomNavType<T> {
    return EMCustomNavType(T::class.java)
}