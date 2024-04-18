package ru.adavydova.remote

import retrofit2.http.GET
import ru.adavydova.remote.models.OffersDto
import ru.adavydova.remote.models.OffersTicketsDto
import ru.adavydova.remote.models.TicketsDto


   interface FlightSearchApi {
      @GET("1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav/view?usp=sharing")
      suspend fun getOffers(): Result<OffersDto>

      @GET("13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta/view?usp=sharing")
      suspend fun getOffersTickets(): Result<OffersTicketsDto>

      @GET("1HogOsz4hWkRwco4kud3isZHFQLUAwNBA/view?usp=sharing")
      suspend fun getTickets(): Result<TicketsDto>
   }
