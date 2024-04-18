package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OffersDto (
    @SerialName("offers")
    val offers: List<OfferDto>
)