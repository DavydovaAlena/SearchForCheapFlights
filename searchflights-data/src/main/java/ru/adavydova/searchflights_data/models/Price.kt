package ru.adavydova.searchflights_data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Price(
    @SerialName("value")
    val value:  Int
)