package ru.adavydova.selectcountry_feature.adapter_delegate

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.adavydova.remote.models.OfferTicket

class CountrySelectAdapter (
    goToTheBack: ()-> Unit,
    showAllStraightRails: ()->Unit,
    selectStraightRailsItem: (OfferTicket)->Unit,
    viewAllTickets: ()-> Unit
): ListDelegationAdapter<List<CountrySelectAdapterItem>>(
    inputFieldsAdapterDelegate(goToBack = goToTheBack),
    chipsAdapterDelegate(),
    directFlightsAdapterDelegate(showAll = showAllStraightRails, clickToItem = selectStraightRailsItem),
    buttonBlockAdapterDelegate(viewAllTickets)

)