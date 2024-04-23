package ru.adavydova.main_screen.adapter_delegate_item

import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.adavydova.ui_component.databinding.InputFieldsBlockBinding
import ru.adavydova.ui_component.databinding.MusicalFlyAwayBlockBinding


fun inputFieldsAdapterDelegate(
    changeCountryFrom: (String) -> Unit,
    selectCountryTo: () -> Unit, ) =
    adapterDelegateViewBinding<InputFieldsItem, DisplayableItem, InputFieldsBlockBinding>({ layoutInflater, parent ->
        InputFieldsBlockBinding.inflate(layoutInflater, parent, false)
    }) {


        bind {
            binding.fromTheCountry.setText(item.cityFrom)
            binding.fromTheCountry.doOnTextChanged { text, _, _, _ ->
                changeCountryFrom(text.toString())
            }
            binding.toTheCountry.setOnClickListener {
                selectCountryTo()
            }
        }
    }

fun offersAdapterDelegate() =
    adapterDelegateViewBinding<OfferItem, DisplayableItem, MusicalFlyAwayBlockBinding>({ layoutInflater, parent ->
        MusicalFlyAwayBlockBinding.inflate(layoutInflater, parent, false)
    }) {

        bind {
            binding.apply {
                val offersAdapter = OffersAdapter(item.offers)
                offersRv.apply {
                    this.adapter = offersAdapter
                    itemAnimator = DefaultItemAnimator()
                    layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
                }
                title.text = item.title
            }
        }
    }