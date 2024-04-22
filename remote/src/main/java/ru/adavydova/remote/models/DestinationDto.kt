package ru.adavydova.remote.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class DestinationDto (
    @SerializedName("town")
    val town: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("airport")
    val airport: String
)