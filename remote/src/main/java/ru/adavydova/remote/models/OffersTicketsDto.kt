package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OffersTicketsDto (
    @SerialName("tickets_offers")
    val ticketsOffers: List<TicketOfferDto>
)