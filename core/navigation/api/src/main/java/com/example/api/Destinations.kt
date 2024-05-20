package com.example.api

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
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
object Blog4

@Serializable
object Blog5

@Serializable
object Posts

@Serializable
@Parcelize
data class SearchParameters(
    val searchQuery: String,
    val filters: List<String>
): Parcelable

@Serializable
data class PostDetails(val parameters: SearchParameters)

val SearchParametersType = object : NavType<SearchParameters>(isNullableAllowed = false) {

    override fun put(bundle: Bundle, key: String, value: SearchParameters) {
        bundle.putParcelable(key, value)
    }
    override fun get(bundle: Bundle, key: String): SearchParameters? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): SearchParameters {
        return Json.decodeFromString<SearchParameters>(value)
    }

    override val name: String = "SearchParameters"
}

@Serializable
object Profile

@Serializable
data class ProfileSettings(val idArg: Int)