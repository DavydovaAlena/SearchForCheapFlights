package ru.adavydova.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

data class OfferDto (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("town")
    val town: String,
    @SerializedName("price")
    val price: PriceDto
)