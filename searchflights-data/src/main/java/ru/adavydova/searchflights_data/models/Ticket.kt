package ru.adavydova.searchflights_data.models

data class Ticket (
    val id: Long,
    val badge: String?,
    val price: Price,
    val providerName: String,
    val company:String,
    val departure: Destination,
    val arrival: Destination,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangable: Boolean

)