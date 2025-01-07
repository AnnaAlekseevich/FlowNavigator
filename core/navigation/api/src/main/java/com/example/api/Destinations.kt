package com.example.api

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
object AuthDestination

@Serializable
object MainDestination

@Serializable
object Blog

@Serializable
object Blog1

@Serializable
object Blog2

@Serializable
object Blog3

@Serializable
object Posts

@Serializable
object Overlay

@Serializable
object BottomSheet

@Serializable
@Parcelize
data class SearchParameters(
    val searchQuery: String,
    val filters: List<String>
): Parcelable

@Serializable
data class PostDetails(val parameters: SearchParameters)

val SearchParametersType = object : NavType<SearchParameters>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): SearchParameters? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, SearchParameters::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): SearchParameters {
        return Json.decodeFromString<SearchParameters>(value)
    }

    override fun put(bundle: Bundle, key: String, value: SearchParameters) {
        bundle.putParcelable(key, value)
    }

    /***
     * An essential point here is overriding the method serializeAsValue which has a default implementation
     * in the NavType abstract class, hence it’s not mandatory to implement it.
     */
    override fun serializeAsValue(value: SearchParameters): String {
        return Json.encodeToString(value)
    }

}

@Serializable
object Profile

@Serializable
data class ProfileSettings(val idArg: Int)