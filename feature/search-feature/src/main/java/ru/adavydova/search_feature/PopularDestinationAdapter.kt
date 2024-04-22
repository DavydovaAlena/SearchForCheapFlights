package ru.adavydova.search_feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import ru.adavydova.remote.models.OfferTicket
import ru.adavydova.ui_component.databinding.PopularDestinationItemBinding

class PopularDestinationAdapter(
    private val goToTheSelectCity: (OfferTicket)-> Unit,
    private val popularDistinctions: List<OfferTicket>
) : RecyclerView.Adapter<PopularDestinationAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = PopularDestinationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val picture = when (position) {
            1 -> ru.adavydova.ui_component.R.drawable.musical_fly_away_1
            2 -> ru.adavydova.ui_component.R.drawable.musical_fly_away_2
            3 -> ru.adavydova.ui_component.R.drawable.musical_fly_away_3
            else -> ru.adavydova.ui_component.R.drawable.musical_fly_away_3
        }
        holder.bind(picture, popularDistinctions[position])
    }

    override fun getItemCount(): Int {
        return popularDistinctions.size
    }

    inner class SearchViewHolder(private val binding: PopularDestinationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(@DrawableRes picture: Int, item: OfferTicket){
                binding.picture.setImageResource(picture)
                binding.destinationTitle.text = item.title
                binding.popularDestinationContainer.setOnClickListener {
                    goToTheSelectCity(item)
                }
            }
    }
}
