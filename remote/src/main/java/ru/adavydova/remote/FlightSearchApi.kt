package ru.adavydova.remote


interface FlightSearchApi {
   suspend fun getOffers()
}