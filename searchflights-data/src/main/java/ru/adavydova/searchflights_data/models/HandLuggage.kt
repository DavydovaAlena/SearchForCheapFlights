package ru.adavydova.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class HandLuggage (
    val hasHandLuggage: Boolean,
    val size: String
)