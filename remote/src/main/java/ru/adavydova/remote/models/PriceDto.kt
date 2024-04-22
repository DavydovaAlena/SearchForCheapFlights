package ru.adavydova.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class PriceDto(
    @SerializedName("value")
    val value:  Int
)