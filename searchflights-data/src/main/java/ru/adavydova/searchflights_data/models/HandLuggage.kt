package ru.adavydova.searchflights_data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class HandLuggage (
    val hasHandLuggage: Boolean,
    val size: String?
)