package ru.adavydova.searchflights_data.usecase

import ru.adavydova.searchflights_data.repository.FlightSearchRepository

class GetTicketsUseCase (
    private val repository: FlightSearchRepository
) {
    suspend operator fun invoke() = repository.getTickets()
}