package ru.adavydova.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.adavydova.remote.models.OffersDto
import ru.adavydova.remote.models.OffersTicketsDto
import ru.adavydova.remote.models.TicketsDto

   interface FlightSearchApi {
      @GET("uc?export=view&id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav")
      suspend fun getOffers(
      ): Result<OffersDto>

      @GET("uc?export=view&id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta")
      suspend fun getOffersTickets(): Result<OffersTicketsDto>

      @GET("uc?export=view&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
      suspend fun getTickets(): Result<TicketsDto>
   }
