package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class DestinationDto (
    @SerialName("town")
    val town: String,
    @SerialName("date")
    val date: Date,
    @SerialName("airport")
    val airport: String
)