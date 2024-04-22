package ru.adavydova.selectcountry_feature

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ru.adavydova.flightsearch_data.utils.Result
import ru.adavydova.remote.models.OfferTicket
import ru.adavydova.searchflights_data.usecase.SearchFlightsUseCases
import ru.adavydova.selectcountry_feature.adapter_delegate.ButtonBlockItem
import ru.adavydova.selectcountry_feature.adapter_delegate.Chip
import ru.adavydova.selectcountry_feature.adapter_delegate.ChipsItem
import ru.adavydova.selectcountry_feature.adapter_delegate.DirectFlightsItem
import ru.adavydova.selectcountry_feature.adapter_delegate.InputFieldsItem
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

data class SetCountryInputFieldsState(
    val cityFrom: String = "",
    val cityTo: String = ""
)

@HiltViewModel
class SelectCountryViewModel @Inject constructor(
    useCases: SearchFlightsUseCases
) : ViewModel() {

    private val straightRailsState = MutableStateFlow<List<OfferTicket>>(emptyList())
    private val chips = MutableStateFlow<List<Chip>>(emptyList())
    private val showAllStraightRailsState = MutableStateFlow<Boolean>(false)
    private val subscribeToPriceState = MutableStateFlow<Boolean>(false)
    private val inputFieldsState = MutableStateFlow(SetCountryInputFieldsState())

    val countrySelectAdapterItemsState = combine(
        straightRailsState, chips, showAllStraightRailsState, subscribeToPriceState, inputFieldsState
    ) { straightRailsState, chips, showAllStraightRailsState, subscribeToPriceState, inputFieldsState ->

        listOf(
            InputFieldsItem(
                cityFrom = inputFieldsState.cityFrom,
                cityTo = inputFieldsState.cityTo
            ),
            ChipsItem(
                items = chips
            ),
            DirectFlightsItem(
                showAllState = showAllStraightRailsState,
                items = straightRailsState
            ),
            ButtonBlockItem(
                subscribeToPriceState = subscribeToPriceState
            )
        )

    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCases.getOffersTicketsUseCase()) {
                is Result.Error -> {
                    Log.e("SelectCountryVM", result.error)
                }

                is Result.Success -> {
                    result.data.collectLatest {
                        straightRailsState.value = it
                    }
                }
            }

            val currentDate = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd LLL, E", Locale("ru"))
            val date = dateFormat.format(currentDate)

            chips.value = listOf(
                Chip(icon = ru.adavydova.ui_component.R.drawable.plus_icon, title = "обратно"),
                Chip(title = date.toString()),
                Chip(title = "1, эконом"),
                Chip(icon = ru.adavydova.ui_component.R.drawable.munu_icon, title = "фильтры")
            )
        }
    }


}