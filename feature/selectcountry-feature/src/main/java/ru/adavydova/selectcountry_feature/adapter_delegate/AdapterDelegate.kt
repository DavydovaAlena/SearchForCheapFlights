package ru.adavydova.selectcountry_feature.adapter_delegate

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.adavydova.remote.models.OfferTicket
import ru.adavydova.selectcountry_feature.databinding.ButtonBlockBinding
import ru.adavydova.selectcountry_feature.databinding.ChipsBlockBinding
import ru.adavydova.selectcountry_feature.databinding.DirectFlightsBlockBinding
import ru.adavydova.selectcountry_feature.databinding.TextFieldsSelectCountryBlockBinding

fun inputFieldsAdapterDelegate(
    goToBack: () -> Unit
) =
    adapterDelegateViewBinding<InputFieldsItem, CountrySelectAdapterItem, TextFieldsSelectCountryBlockBinding>(
        { layoutInflater, parent ->
            TextFieldsSelectCountryBlockBinding.inflate(layoutInflater, parent, false)
        }) {
        bind {
            binding.cityFrom.text = item.cityFrom
            binding.cityTo.setText(item.cityTo)

            binding.clearButton.setOnClickListener {
                binding.cityTo.text = null
            }

            binding.arrow.setOnClickListener {
                goToBack()
            }

            binding.changePositionButton.setOnClickListener {
                binding.cityFrom.text = binding.cityTo.text
                binding.cityTo.setText(binding.cityFrom.text)
            }
        }
    }

fun chipsAdapterDelegate() = adapterDelegateViewBinding<ChipsItem, CountrySelectAdapterItem, ChipsBlockBinding>({ layoutInflater, parent ->
    ChipsBlockBinding.inflate(layoutInflater, parent, false)
}){
    bind {
        val adapter = ChipsAdapter(item.items)
        binding.chipsRv.apply {
            layoutManager =  LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            this.adapter = adapter
        }
    }
}

fun directFlightsAdapterDelegate(
    showAll:()-> Unit,
    clickToItem: (OfferTicket)-> Unit
) = adapterDelegateViewBinding<DirectFlightsItem, CountrySelectAdapterItem, DirectFlightsBlockBinding>({layoutInflater, parent ->
    DirectFlightsBlockBinding.inflate(layoutInflater, parent, false)
}){
    bind {

        val adapter = when (item.showAllState){
            true -> DirectFlightAdapter(item.items, clickToItem)
            false -> DirectFlightAdapter(item.items.subList(0,2), clickToItem)
        }

        when(item.showAllState){
            true -> { binding.showAllButton.visibility = View.GONE }
            false -> {binding.showAllButton.visibility = View.VISIBLE}
        }

        binding.popularDestinationRv.apply {
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            this.adapter = adapter
        }
        binding.showAllButton.setOnClickListener{
            showAll()
        }
    }
}

fun buttonBlockAdapterDelegate(
    viewAllTickets: ()-> Unit
) = adapterDelegateViewBinding<ButtonBlockItem, CountrySelectAdapterItem, ButtonBlockBinding>({layoutInflater, parent ->
    ButtonBlockBinding.inflate(layoutInflater, parent ,false)
}){
    bind {
        binding.showAllButton.setOnClickListener {
            viewAllTickets()
        }
        binding.switchElement.setOnCheckedChangeListener { buttonView, isChecked ->

        }
    }
}