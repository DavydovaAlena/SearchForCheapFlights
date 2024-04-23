package ru.adavydova.searchflights_data.mapper

import ru.adavydova.searchflights_data.models.Offer
import ru.adavydova.remote.models.OfferDto
import ru.adavydova.searchflights_data.models.Price

fun OfferDto.toOffer() = Offer(
    id = id,
    title = title,
    town = town,
    price = Price(
        value = price.value
    )
)