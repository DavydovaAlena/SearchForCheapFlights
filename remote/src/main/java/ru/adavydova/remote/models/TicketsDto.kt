package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsDto (
    @SerialName("tickets")
    val tickets: List<TicketDto>
)