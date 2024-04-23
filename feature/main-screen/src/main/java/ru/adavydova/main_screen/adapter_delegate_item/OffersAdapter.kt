package ru.adavydova.main_screen.adapter_delegate_item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import ru.adavydova.searchflights_data.models.Offer
import ru.adavydova.ui_component.adapter_delegate.utils.priceFormatter
import ru.adavydova.ui_component.databinding.OfferItemBinding

class OffersAdapter(private val items: List<Offer>) :
    RecyclerView.Adapter<OffersAdapter.OffersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        val binding = OfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OffersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        val picture = when (position) {
            1 -> ru.adavydova.ui_component.R.drawable.musical_fly_away_1
            2 -> ru.adavydova.ui_component.R.drawable.musical_fly_away_2
            3 -> ru.adavydova.ui_component.R.drawable.musical_fly_away_3
            else -> ru.adavydova.ui_component.R.drawable.musical_fly_away_3
        }
        holder.bind(picture, items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class OffersViewHolder(private val binding: OfferItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(@DrawableRes picture: Int, offer: Offer) {
            binding.picture.setImageResource(picture)
            binding.artist.text = offer.title
            binding.price.text = offer.price.value.priceFormatter()
        }
    }
}