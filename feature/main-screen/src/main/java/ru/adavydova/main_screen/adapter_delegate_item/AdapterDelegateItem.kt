package ru.adavydova.main_screen.adapter_delegate_item

import ru.adavydova.remote.models.Offer


data class InputFieldsItem(
    val titleBlock: String,
    val cityFrom: String?,
    val cityTo: String?,
) : DisplayableItem

data class OfferItem(
    val title: String,
    val offers: List<Offer>,
    val buttonAllPlacesText: String? = null,
    val enableButtonAllPlaces: Boolean = false
) : DisplayableItem

