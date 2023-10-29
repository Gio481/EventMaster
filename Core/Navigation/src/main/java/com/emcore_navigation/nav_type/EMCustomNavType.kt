package com.emcore_navigation.nav_type

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson

/**
 * [EMCustomNavType] Built In NavTypes don't support to use objects as the arguments
 * that's why we need to create our Custom NavType, overridden functions are the necessary
 * to use object Arguments
 *
 * @param clazz is the Class which we need to use as a Argument
 */
class EMCustomNavType<T : Parcelable>(
    private val clazz: Class<T>
) : NavType<T>(isNullableAllowed = true) {

    /**
     * [get] Gets the parcelable object from the bundle
     */
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    /**
     * Parses a JSON string into an instance of the specified type.
     *
     * @param value The JSON string to be parsed.
     * @return [T] The type of the object to be deserialized from the JSON string and it is
     * the specified type object created from the JSON string.
     */
    override fun parseValue(value: String): T {
        return Gson().fromJson(value, clazz)
    }

    /**
     * [get] Puts the parcelable object in the bundle
     */
    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    /**
     * [serializeAsValue] Serializes an object of the specified type into a JSON string, with
     * the function, navigation perceives object as a String
     *
     * @param value The object to be serialized into a JSON string.
     * @return [T] A JSON string representation of the transformed object.
     */
    override fun serializeAsValue(value: T): String {
        return Uri.encode(Gson().toJson(value))
    }
}