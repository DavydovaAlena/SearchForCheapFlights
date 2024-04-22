package ru.adavydova.search_feature

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.adavydova.flightsearch_data.utils.Result
import ru.adavydova.remote.models.OfferTicket
import ru.adavydova.searchflights_data.usecase.SearchFlightsUseCases
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: SearchFlightsUseCases
) : ViewModel() {


    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    private val _offersTickets = MutableStateFlow<List<OfferTicket>>(emptyList())
    val offersTickets = _offersTickets.asStateFlow()

    init {
        onEvent(SearchEvent.GetOfferTickets)
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            SearchEvent.GetOfferTickets -> {
                viewModelScope.launch(Dispatchers.IO){
                    when(val result = useCases.getOffersTicketsUseCase()){
                        is Result.Error -> {
                            Log.e("SearchVM", result.error)
                        }
                        is Result.Success -> {
                            result.data.collectLatest {
                                _offersTickets.value = it
                            }
                        }
                    }
                }
            }

            is SearchEvent.UpdateCityFrom -> {
                _searchState.value = _searchState.value.copy(cityFrom = event.city)
            }
        }
    }
}

sealed class SearchEvent {
    data object GetOfferTickets : SearchEvent()
    class UpdateCityFrom(val city: String) : SearchEvent()
}

data class SearchState(
    val cityFrom: String = "",
    val cityTo: String? = null,
)