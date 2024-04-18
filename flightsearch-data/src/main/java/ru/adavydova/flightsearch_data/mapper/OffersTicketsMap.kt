package ru.adavydova.flightsearch_data.mapper

import ru.adavydova.remote.models.Price
import ru.adavydova.remote.models.TicketOffer
import ru.adavydova.remote.models.TicketOfferDto

fun TicketOfferDto.toTicketOffer() = TicketOffer(
    id = id,
    title = title,
    timeRange = timeRange,
    price = Price(price.current)
)