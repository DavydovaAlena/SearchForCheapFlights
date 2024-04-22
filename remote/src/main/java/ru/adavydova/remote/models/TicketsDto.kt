package ru.adavydova.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsDto (
    @SerializedName("tickets")
    val tickets: List<TicketDto>
)