package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class Offer(
    val id: Long,
    val title: String,
    val town: String,
    val price: Price
)