package ru.adavydova.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HandLuggageDto (
    @SerializedName("has_hand_luggage")val hasHandLuggage: Boolean,
    @SerializedName("size")val size: String
)