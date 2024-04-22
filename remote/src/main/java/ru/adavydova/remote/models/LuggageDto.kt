package ru.adavydova.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LuggageDto (
    @SerializedName("has_luggage")val hasLuggage: Boolean,
    @SerializedName("price")val price: PriceDto
)