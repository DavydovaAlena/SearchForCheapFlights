package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class TicketOffer (
   val id: Long,
   val title: String,
   val timeRange: List<String>,
   val price: Price
)