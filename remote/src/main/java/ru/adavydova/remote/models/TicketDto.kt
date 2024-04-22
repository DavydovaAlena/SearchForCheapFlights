package ru.adavydova.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketDto (
    @SerializedName("id")val id: Long,
    @SerializedName("badge")val badge: String,
    @SerializedName("price")val price: PriceDto,
    @SerializedName("provider_name")val providerName: String,
    @SerializedName("company")val company:String,
    @SerializedName("departure")val departure: DestinationDto,
    @SerializedName("arrival")val arrival: DestinationDto,
    @SerializedName("has_transfer")val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer")val hasVisaTransfer: Boolean,
    @SerializedName("luggage")val luggage: LuggageDto,
    @SerializedName("hand_luggage")val handLuggageDto: HandLuggageDto,
    @SerializedName("is_returnable")val isReturnable: Boolean,
    @SerializedName("is_exchangable")val isExchangable: Boolean

)