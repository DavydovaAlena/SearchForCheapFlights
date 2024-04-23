package ru.adavydova.selectcountry_feature.adapter_delegate

import android.text.TextWatcher
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.adavydova.searchflights_data.models.OfferTicket
import ru.adavydova.selectcountry_feature.databinding.ButtonBlockBinding
import ru.adavydova.selectcountry_feature.databinding.ChipsBlockBinding
import ru.adavydova.selectcountry_feature.databinding.DirectFlightsBlockBinding
import ru.adavydova.selectcountry_feature.databinding.TextFieldsSelectCountryBlockBinding




fun inputFieldsAdapterDelegate(
    goToBack: () -> Unit,
    updateCityFrom: (String?) -> Unit,
    updateCityTo: (String?) -> Unit,
    swapCities:()-> Unit
) =
    adapterDelegateViewBinding<InputFieldsItem, CountrySelectAdapterItem, TextFieldsSelectCountryBlockBinding>(
        { layoutInflater, parent ->
            TextFieldsSelectCountryBlockBinding.inflate(layoutInflater, parent, false)
        }) {
        bind {
            binding.apply {
                cityFrom.text = item.cityFrom

                clearButton.setOnClickListener { cityTo.text = null }
                cityTo.setText(item.cityTo)
                cityTo.setSelection(cityTo.text.length)

                cityTo.doOnTextChanged { text, _, _, _ ->
                    updateCityTo(text.toString())
                }
                arrow.setOnClickListener { goToBack() }


                changePositionButton.setOnClickListener {
                    val lastCityTo = cityTo.text
                    cityTo.setText(cityFrom.text)
                    cityFrom.text = lastCityTo
                    updateCityTo(cityTo.text.toString())
                    updateCityFrom(cityFrom.text.toString())
                }
            }
        }
    }

fun chipsAdapterDelegate(
    openCalendar: () -> Unit
) =
    adapterDelegateViewBinding<ChipsItem, CountrySelectAdapterItem, ChipsBlockBinding>({ layoutInflater, parent ->
        ChipsBlockBinding.inflate(layoutInflater, parent, false)
    }) {
        bind {
            val adapter = ChipsAdapter(item.items, openCalendar)
            binding.chipsRv.apply {
                layoutManager =
                    LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
                itemAnimator = DefaultItemAnimator()
                this.adapter = adapter
            }
        }
    }

fun directFlightsAdapterDelegate(
    showAll: () -> Unit,
    clickToItem: (OfferTicket) -> Unit
) =
    adapterDelegateViewBinding<DirectFlightsItem, CountrySelectAdapterItem, DirectFlightsBlockBinding>(
        { layoutInflater, parent ->
            DirectFlightsBlockBinding.inflate(layoutInflater, parent, false)
        }) {
        bind {

            val adapter = when (item.showAllState || item.items.size < 3) {
                true -> DirectFlightAdapter(item.items, clickToItem)
                false -> DirectFlightAdapter(item.items.subList(0, 2), clickToItem)
            }
            when (item.showAllState) {
                true -> {
                    binding.showAllButton.visibility = View.GONE
                }

                false -> {
                    binding.showAllButton.visibility = View.VISIBLE
                }
            }

            binding.popularDestinationRv.apply {
                layoutManager = LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
                this.adapter = adapter
            }
            binding.showAllButton.setOnClickListener {
                showAll()
            }
        }
    }

fun buttonBlockAdapterDelegate(
    viewAllTickets: () -> Unit,
    subscribeToThePrice: (Boolean) -> Unit
) =
    adapterDelegateViewBinding<ButtonBlockItem, CountrySelectAdapterItem, ButtonBlockBinding>({ layoutInflater, parent ->
        ButtonBlockBinding.inflate(layoutInflater, parent, false)
    }) {
        bind {
            binding.showAllButton.setOnClickListener { viewAllTickets() }
            binding.switchElement.setOnCheckedChangeListener(null)
            binding.switchElement.isChecked = item.subscribeToPriceState
            binding.switchElement.setOnCheckedChangeListener { _, isChecked ->
                subscribeToThePrice(isChecked)
            }
        }
    }