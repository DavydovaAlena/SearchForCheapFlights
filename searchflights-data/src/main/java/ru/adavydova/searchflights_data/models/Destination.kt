package ru.adavydova.searchflights_data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date


data class Destination (
    val town: String,
    val date: String,
    val airport: String
)