package ru.adavydova.searchforcheapflights.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.adavydova.searchflights_data.repository.FlightSearchRepository
import ru.adavydova.searchflights_data.repository.FlightSearchRepositoryImpl
import ru.adavydova.remote.FlightSearchApi
import ru.adavydova.searchflights_data.usecase.GetOffersTicketsUseCase
import ru.adavydova.searchflights_data.usecase.GetOffersUseCase
import ru.adavydova.searchflights_data.usecase.GetTicketsUseCase
import ru.adavydova.searchflights_data.usecase.ReadTheSavedCountry
import ru.adavydova.searchflights_data.usecase.SavingTheCountryUseCase
import ru.adavydova.searchflights_data.usecase.SearchFlightsUseCases
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SearchFlightsModule {

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideFlightSearchApi(client: OkHttpClient, gson: Gson): FlightSearchApi {
        return Retrofit.Builder()
            .baseUrl("https://drive.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .client(client)
            .build()
            .create(FlightSearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFlightSearchRepository(api: FlightSearchApi): FlightSearchRepository {
        return FlightSearchRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSearchFlightsUseCases(
        context: Application,
        repository: FlightSearchRepository
    ): SearchFlightsUseCases {
        return SearchFlightsUseCases(
            getOffersTicketsUseCase = GetOffersTicketsUseCase(repository = repository),
            getOffersUseCase = GetOffersUseCase(repository),
            getTicketsUseCase = GetTicketsUseCase(repository),
            savingTheCountryUseCase = SavingTheCountryUseCase(context.applicationContext),
            readTheSavedCountry = ReadTheSavedCountry(context.applicationContext)
        )
    }
}