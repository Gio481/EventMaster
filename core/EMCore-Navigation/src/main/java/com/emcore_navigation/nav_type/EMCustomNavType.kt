package com.emcore_navigation.nav_type

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson

class EMCustomNavType<T : Parcelable>(
    private val clazz: Class<T>
) : NavType<T>(isNullableAllowed = true) {

    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T {
        return Gson().fromJson(value, clazz)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}