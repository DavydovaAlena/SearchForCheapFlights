package ru.adavydova.searchflights_data.mapper

import ru.adavydova.searchflights_data.models.Price
import ru.adavydova.searchflights_data.models.OfferTicket
import ru.adavydova.remote.models.TicketOfferDto

fun TicketOfferDto.toTicketOffer() = OfferTicket(
    id = id,
    title = title,
    timeRange = timeRange,
    price = Price(price.value)
)