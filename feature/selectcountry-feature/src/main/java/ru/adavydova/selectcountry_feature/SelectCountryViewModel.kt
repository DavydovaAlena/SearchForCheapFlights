package ru.adavydova.selectcountry_feature

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
import kotlinx.coroutines.launch
import ru.adavydova.flightsearch_data.utils.Result
import ru.adavydova.searchflights_data.models.OfferTicket
import ru.adavydova.searchflights_data.usecase.SearchFlightsUseCases
import ru.adavydova.selectcountry_feature.adapter_delegate.ButtonBlockItem
import ru.adavydova.selectcountry_feature.adapter_delegate.Chip
import ru.adavydova.selectcountry_feature.adapter_delegate.ChipsItem
import ru.adavydova.selectcountry_feature.adapter_delegate.DirectFlightsItem
import ru.adavydova.selectcountry_feature.adapter_delegate.InputFieldsItem
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

data class SetCountryInputFieldsState(
    val cityFrom: String? = null,
    val cityTo: String? = null
)

data class StraightRailsState(
    val offerTickets: List<OfferTicket> = emptyList(),
    val showAllStraightRails: Boolean = false
)

sealed class SelectCountryEvent {
    data object ShowAllStraightRails : SelectCountryEvent()
    class ChangeTheDepartureTime(val date: String) : SelectCountryEvent()
    class SubscribeToThePrice(val state: Boolean) : SelectCountryEvent()
    data object SwapCities : SelectCountryEvent()
    data object ClearCityToValue : SelectCountryEvent()
    class ChangeCityToState(val cityTo: String?) : SelectCountryEvent()
    class ChangeCityFromState(val cityFrom: String?) : SelectCountryEvent()
}

@HiltViewModel
class SelectCountryViewModel @Inject constructor(
    private val useCases: SearchFlightsUseCases
) : ViewModel() {

    private val _straightRailsState = MutableStateFlow(StraightRailsState())
    val straightRailsState = _straightRailsState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCases.getOffersTicketsUseCase()) {
                is Result.Error -> {
                    Log.e("SelectCountryVM", result.error)
                }

                is Result.Success -> {
                    result.data.collectLatest {
                        _straightRailsState.value =
                            _straightRailsState.value.copy(offerTickets = it)
                    }
                }
            }
            val currentDate = Calendar.getInstance().time
            val date = converterDate(currentDate)

            _chips.value = listOf(
                Chip(icon = ru.adavydova.ui_component.R.drawable.plus_icon, title = "обратно"),
                Chip(title = date.toString()),
                Chip(title = "1, эконом"),
                Chip(icon = ru.adavydova.ui_component.R.drawable.munu_icon, title = "фильтры")
            )
        }
    }

    private val _chips = MutableStateFlow<List<Chip>>(emptyList())
    val chips = _chips.asStateFlow()

    private val _subscribeToPriceState = MutableStateFlow<Boolean>(false)
    val subscribeToPriceState = _subscribeToPriceState.asStateFlow()

    private val _inputFieldsState = MutableStateFlow(SetCountryInputFieldsState())
    val inputFieldsState = _inputFieldsState.asStateFlow()


    val countrySelectAdapterItemsState = combine(
        _straightRailsState,
        _chips,
        _subscribeToPriceState,
        _inputFieldsState
    ) { straightRailsState, chips, subscribeToPriceState, inputFieldsState ->

        listOf(
            InputFieldsItem(
                cityFrom = inputFieldsState.cityFrom,
                cityTo = inputFieldsState.cityTo
            ),
            ChipsItem(
                items = chips
            ),
            DirectFlightsItem(
                showAllState = straightRailsState.showAllStraightRails,
                items = straightRailsState.offerTickets
            ),
            ButtonBlockItem(
                subscribeToPriceState = subscribeToPriceState
            )
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    private fun converterDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd LLL, E", Locale("ru"))
        return dateFormat.format(date)
    }

    fun onEvent(event: SelectCountryEvent) {
        when (event) {
            is SelectCountryEvent.ChangeTheDepartureTime -> {
                val currentState = _chips.value.toMutableList()
                currentState.elementAtOrNull(1)?.let {
                    val newValue = Chip(title = event.date)
                    currentState[1] = newValue
                    _chips.value = currentState
                }
            }

            SelectCountryEvent.ClearCityToValue -> {
                _inputFieldsState.value = _inputFieldsState.value.copy(cityTo = null)
            }

            SelectCountryEvent.ShowAllStraightRails -> {
                _straightRailsState.value =
                    _straightRailsState.value.copy(showAllStraightRails = !_straightRailsState.value.showAllStraightRails)
            }

            is SelectCountryEvent.SubscribeToThePrice -> {
                _subscribeToPriceState.value = event.state
            }

            SelectCountryEvent.SwapCities -> {
                _inputFieldsState.value = _inputFieldsState.value.copy(
                    cityFrom = _inputFieldsState.value.cityTo,
                    cityTo = _inputFieldsState.value.cityFrom
                )
            }

            is SelectCountryEvent.ChangeCityFromState -> {
                _inputFieldsState.value = _inputFieldsState.value.copy(
                    cityFrom = event.cityFrom,
                )
            }

            is SelectCountryEvent.ChangeCityToState -> {
                _inputFieldsState.value = _inputFieldsState.value.copy(
                    cityTo = event.cityTo,
                )
            }
        }
    }


}