package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class Luggage (
    val hasLuggage: Boolean,
    val price: Price
)