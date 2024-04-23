package ru.adavydova.selectcountry_feature.adapter_delegate

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.adavydova.searchflights_data.models.OfferTicket

class CountrySelectAdapter (
    goToTheBack: ()-> Unit,
    showAllStraightRails: ()->Unit,
    selectStraightRailsItem: (OfferTicket)->Unit,
    updateCityFrom: (String?)->Unit,
    subscribeToThePrice: (Boolean)->Unit,
    viewAllTickets: ()-> Unit,
    updateCityTo: (String?)-> Unit,
    openCalendar: ()-> Unit
): ListDelegationAdapter<List<CountrySelectAdapterItem>>(
    inputFieldsAdapterDelegate(goToBack = goToTheBack, updateCityFrom = updateCityFrom , updateCityTo = updateCityTo),
    chipsAdapterDelegate(openCalendar),
    directFlightsAdapterDelegate(showAll = showAllStraightRails, clickToItem = selectStraightRailsItem),
    buttonBlockAdapterDelegate(viewAllTickets, subscribeToThePrice)

)