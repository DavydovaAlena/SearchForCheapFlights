package ru.adavydova.main_screen.adapter_delegate_item

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.flow.StateFlow
import ru.adavydova.remote.models.Offer

class MainScreenAdapter(
    changeCountryFrom: (String) -> Unit,
    selectCountryTo: () -> Unit,
): ListDelegationAdapter<List<DisplayableItem>>(
    inputFieldsAdapterDelegate(changeCountryFrom, selectCountryTo, ),
    offersAdapterDelegate()

)