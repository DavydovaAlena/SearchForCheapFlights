package ru.adavydova.allticket_feature

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.adavydova.flightsearch_data.utils.Result
import ru.adavydova.searchflights_data.models.Ticket
import ru.adavydova.searchflights_data.usecase.SearchFlightsUseCases
import javax.inject.Inject

@HiltViewModel
class AllTicketViewModel @Inject constructor(
    private val useCases: SearchFlightsUseCases
) : ViewModel() {

    private val _tickets = MutableStateFlow<List<Ticket>>(emptyList())
    val tickets = _tickets.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = useCases.getTicketsUseCase()){
                is Result.Error -> {
                    Log.e("AllTicketVM", result.error)
                }
                is Result.Success -> {
                    result.data.collectLatest {
                        _tickets.value = it
                    }
                }
            }
        }
    }
}