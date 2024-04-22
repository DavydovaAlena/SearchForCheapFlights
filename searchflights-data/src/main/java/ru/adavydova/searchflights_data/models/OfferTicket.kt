package ru.adavydova.remote.models

data class OfferTicket (
   val id: Int,
   val title: String,
   val timeRange: List<String>,
   val price: Price
)