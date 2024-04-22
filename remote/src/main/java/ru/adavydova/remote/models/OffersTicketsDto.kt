package ru.adavydova.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OffersTicketsDto (
    @SerializedName("tickets_offers")
    val ticketsOffers: List<TicketOfferDto>
)