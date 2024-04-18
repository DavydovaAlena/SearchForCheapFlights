package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LuggageDto (
    @SerialName("has_luggage")val hasLuggage: Boolean,
    @SerialName("price")val price: PriceDto
)