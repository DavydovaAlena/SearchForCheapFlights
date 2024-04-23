package ru.adavydova.selectcountry_feature.adapter_delegate

import androidx.annotation.DrawableRes
import ru.adavydova.searchflights_data.models.OfferTicket

interface CountrySelectAdapterItem

data class Chip(
    val title: String,
    @DrawableRes val icon: Int? = null,
    val subTitle: String? = null
)
data class InputFieldsItem(
    val cityFrom: String?,
    val cityTo: String?
): CountrySelectAdapterItem

data class ChipsItem(
    val items: List<Chip>
): CountrySelectAdapterItem

data class DirectFlightsItem(
    val title: String = "Прямые рейсы",
    val showAllState: Boolean,
    val items: List<OfferTicket>
): CountrySelectAdapterItem

data class ButtonBlockItem(
    val subscribeToPriceState: Boolean
): CountrySelectAdapterItem