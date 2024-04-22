package ru.adavydova.flightsearch_data.mapper

import ru.adavydova.remote.models.Offer
import ru.adavydova.remote.models.OfferDto
import ru.adavydova.remote.models.Price

fun OfferDto.toOffer() = Offer(
    id = id,
    title = title,
    town = town,
    price = Price(
        value = price.value
    )
)