package ru.adavydova.searchflights_data.models

data class OfferTicket (
   val id: Int,
   val title: String,
   val timeRange: List<String>,
   val price: Price
)