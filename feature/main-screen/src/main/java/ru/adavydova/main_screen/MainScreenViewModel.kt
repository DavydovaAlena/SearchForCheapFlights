package ru.adavydova.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.adavydova.flightsearch_data.utils.Result
import ru.adavydova.main_screen.adapter_delegate_item.InputFieldsItem
import ru.adavydova.main_screen.adapter_delegate_item.OfferItem
import ru.adavydova.searchflights_data.models.Offer
import ru.adavydova.searchflights_data.usecase.SearchFlightsUseCases
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCases: SearchFlightsUseCases
) : ViewModel() {


    private val _cityFromState = MutableStateFlow<String?>(null)
    val cityFromState = _cityFromState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            onEvent(MainScreenEvent.GetOffers)
            useCases.readTheSavedCountry().collectLatest{
                _cityFromState.value = it
            }
        }
    }

    private val _firstOpening = MutableStateFlow<Boolean>(true)
    val firstOpening = _firstOpening.asStateFlow()

    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers = _offers.asStateFlow()

    val displayedItemsState = combine(offers, cityFromState) { offers, cityFromState ->
        listOf(
            InputFieldsItem(
                titleBlock = "Поиск дешевых авиабилетов",
                cityFrom = cityFromState,
                cityTo = null
            ),
            OfferItem(
                title = "Музыкально отлететь",
                offers = offers
            )
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())




    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.UpdateFromTheCityValue -> {
                viewModelScope.launch(Dispatchers.Default) {
                    useCases.savingTheCountryUseCase(event.query)
                }
            }

            MainScreenEvent.ChangeStateCityFromInitial -> {
                _firstOpening.value  = false
            }

            MainScreenEvent.GetOffers -> {
                viewModelScope.launch(Dispatchers.IO) {
                    when (val result = useCases.getOffersUseCase()) {
                        is Result.Error -> {
                            Log.e("MainScreenVM", result.error)
                        }

                        is Result.Success -> {
                            result.data.collectLatest { offers ->
                                _offers.value =  offers
                            }
                        }
                    }
                }
            }
        }
    }
}


sealed class MainScreenEvent {
    class UpdateFromTheCityValue(val query: String) : MainScreenEvent()
    object GetOffers : MainScreenEvent()
    object ChangeStateCityFromInitial : MainScreenEvent()

}

