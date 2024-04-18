package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketOfferDto (
    @SerialName("id")val id: Long,
    @SerialName("title")val title: String,
    @SerialName("time_range")val timeRange: List<String>,
    @SerialName("price")val price: PriceDto
)