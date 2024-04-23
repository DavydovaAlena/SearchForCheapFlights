package ru.adavydova.flightsearch_data.mapper

import ru.adavydova.searchflights_data.models.Destination
import ru.adavydova.searchflights_data.models.HandLuggage
import ru.adavydova.searchflights_data.models.Luggage
import ru.adavydova.searchflights_data.models.Price
import ru.adavydova.searchflights_data.models.Ticket
import ru.adavydova.remote.models.TicketDto

fun TicketDto.toTicket() = Ticket(
    id = id,
    badge = badge,
    price = Price(price.value),
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
        price = Price(luggage.price.value)
    ),
    handLuggage = HandLuggage(
        hasHandLuggage = handLuggageDto.hasHandLuggage,
        size = handLuggageDto.size
    ),
    isReturnable = isReturnable,
    isExchangable = isExchangable

)