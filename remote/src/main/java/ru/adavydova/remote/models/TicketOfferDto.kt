package ru.adavydova.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketOfferDto (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("time_range")
    val timeRange: List<String>,
    @SerializedName("price")
    val price: PriceDto
)