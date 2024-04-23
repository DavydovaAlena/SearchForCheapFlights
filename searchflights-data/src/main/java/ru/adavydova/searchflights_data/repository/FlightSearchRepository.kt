package ru.adavydova.searchflights_data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.adavydova.searchflights_data.mapper.toOffer
import ru.adavydova.flightsearch_data.mapper.toTicket
import ru.adavydova.searchflights_data.mapper.toTicketOffer
import ru.adavydova.flightsearch_data.utils.Result
import ru.adavydova.remote.FlightSearchApi
import ru.adavydova.searchflights_data.models.Offer
import ru.adavydova.searchflights_data.models.Ticket
import ru.adavydova.searchflights_data.models.OfferTicket

interface FlightSearchRepository {
    suspend fun getOffers(): Result<Flow<List<Offer>>>
    suspend fun getOffersTickets(): Result<Flow<List<OfferTicket>>>
    suspend fun getTickets(): Result<Flow<List<Ticket>>>
}

class FlightSearchRepositoryImpl(
    private val api: FlightSearchApi
) : FlightSearchRepository {
    override suspend fun getOffers(): Result<Flow<List<Offer>>> {
        return api.getOffers().fold(
            onSuccess = {
                val offers = it.offers.map { offerDto -> offerDto.toOffer() }
                Result.Success(
                    data = flowOf(offers)
                )
            },
            onFailure = {
                it.printStackTrace()
                Result.Error(
                    error = it.message ?: "Error when receiving offers"
                )
            }
        )
    }


    override suspend fun getOffersTickets(): Result<Flow<List<OfferTicket>>> {
        val offerTickets = api.getOffersTickets()
        return offerTickets.fold(
            onSuccess = {
                val offersTickets =
                    it.ticketsOffers.map { ticketOfferDto -> ticketOfferDto.toTicketOffer() }
                Result.Success(
                    data = flowOf(offersTickets)
                )
            },
            onFailure = {
                it.printStackTrace()
                Result.Error(
                    error = it.message ?: "Error when receiving offers tickets"
                )
            }
        )
    }

    override suspend fun getTickets(): Result<Flow<List<Ticket>>> {
        return api.getTickets().fold(
            onSuccess = {
                val offersTickets = it.tickets.map { ticketOfferDto -> ticketOfferDto.toTicket() }
                Result.Success(
                    data = flowOf(offersTickets)
                )
            },
            onFailure = {
                it.printStackTrace()
                Result.Error(
                    error = it.message ?: "Error when receiving tickets"
                )
            }
        )
    }
}