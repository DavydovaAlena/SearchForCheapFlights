package ru.adavydova.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

data class OffersDto (
    @SerializedName("offers")
    val offers: List<OfferDto>
)