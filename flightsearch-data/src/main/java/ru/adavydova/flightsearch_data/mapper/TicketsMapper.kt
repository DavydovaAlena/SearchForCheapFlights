package ru.adavydova.flightsearch_data.mapper

import ru.adavydova.remote.models.Destination
import ru.adavydova.remote.models.HandLuggage
import ru.adavydova.remote.models.Luggage
import ru.adavydova.remote.models.Price
import ru.adavydova.remote.models.Ticket
import ru.adavydova.remote.models.TicketDto

fun TicketDto.toTicket() = Ticket(
    id = id,
    badge = badge,
    price = Price(price.current),
    providerName = providerName,
    company = company,
    departure = Destination(
        town = departure.town,
        date = departure.date,
        airport = departure.airport
    ),
    arrival = Destination(
        town = arrival.town,
        date = arrival.date,
        airport = arrival.airport
    ),
    hasTransfer = hasTransfer,
    hasVisaTransfer = hasVisaTransfer,
    luggage = Luggage(
        hasLuggage = luggage.hasLuggage,
        price = Price(luggage.price.current)
    ),
    handLuggage = HandLuggage(
        hasHandLuggage = handLuggageDto.hasHandLuggage,
        size = handLuggageDto.size
    ),
    isReturnable = isReturnable,
    isExchangable = isExchangable

)