package ru.adavydova.selectcountry_feature.adapter_delegate

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.adavydova.searchflights_data.models.OfferTicket

class CountrySelectAdapter (
    goToTheBack: ()-> Unit,
    showAllStraightRails: ()->Unit,
    selectStraightRailsItem: (OfferTicket)->Unit,
    updateCityFrom: (String?)->Unit,
    subscribeToThePrice: (Boolean)->Unit,
    swapCities: ()->Unit,
    viewAllTickets: ()-> Unit,
    updateCityTo: (String?)-> Unit,
    openCalendar: ()-> Unit
): ListDelegationAdapter<List<CountrySelectAdapterItem>>(
    inputFieldsAdapterDelegate(goToBack = goToTheBack, updateCityFrom = updateCityFrom , updateCityTo = updateCityTo, swapCities = swapCities),
    chipsAdapterDelegate(openCalendar),
    directFlightsAdapterDelegate(showAll = showAllStraightRails, clickToItem = selectStraightRailsItem),
    buttonBlockAdapterDelegate(viewAllTickets, subscribeToThePrice)

)