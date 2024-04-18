package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketDto (
    @SerialName("id")val id: Long,
    @SerialName("badge")val badge: String,
    @SerialName("price")val price: PriceDto,
    @SerialName("provider_name")val providerName: String,
    @SerialName("company")val company:String,
    @SerialName("departure")val departure: DestinationDto,
    @SerialName("arrival")val arrival: DestinationDto,
    @SerialName("has_transfer")val hasTransfer: Boolean,
    @SerialName("has_visa_transfer")val hasVisaTransfer: Boolean,
    @SerialName("luggage")val luggage: LuggageDto,
    @SerialName("hand_luggage")val handLuggageDto: HandLuggageDto,
    @SerialName("is_returnable")val isReturnable: Boolean,
    @SerialName("is_exchangable")val isExchangable: Boolean

)