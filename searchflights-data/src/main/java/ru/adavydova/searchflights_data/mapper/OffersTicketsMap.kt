package ru.adavydova.flightsearch_data.mapper

import ru.adavydova.remote.models.Price
import ru.adavydova.remote.models.OfferTicket
import ru.adavydova.remote.models.TicketOfferDto

fun TicketOfferDto.toTicketOffer() = OfferTicket(
    id = id,
    title = title,
    timeRange = timeRange,
    price = Price(price.value)
)