package ru.adavydova.searchflights_data.usecase

data class SearchFlightsUseCases (
    val getOffersTicketsUseCase: GetOffersTicketsUseCase,
    val getOffersUseCase: GetOffersUseCase,
    val getTicketsUseCase: GetTicketsUseCase,
    val savingTheCountryUseCase: SavingTheCountryUseCase,
    val readTheSavedCountry: ReadTheSavedCountry
)