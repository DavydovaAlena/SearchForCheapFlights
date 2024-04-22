package ru.adavydova.selectcountry_feature.adapter_delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.adavydova.remote.models.OfferTicket
import ru.adavydova.selectcountry_feature.databinding.DirectFlightItemBinding

class DirectFlightAdapter(
    private val items: List<OfferTicket>,
    private val onClickToTheItem: (OfferTicket)-> Unit) :
    RecyclerView.Adapter<DirectFlightAdapter.DirectFlightViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectFlightViewHolder {
        val binding =
            DirectFlightItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DirectFlightViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DirectFlightViewHolder, position: Int) {
        val item = items[position]
        val iconColor = when (position) {
            0 -> ru.adavydova.ui_component.R.color.red
            1 -> ru.adavydova.ui_component.R.color.blue
            2 -> ru.adavydova.ui_component.R.color.basic_white
            else -> ru.adavydova.ui_component.R.color.basic_white
        }
        holder.bind(item, iconColor)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class DirectFlightViewHolder(
        private val binding: DirectFlightItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OfferTicket, iconColor: Int) {
            val price = item.timeRange.joinToString(separator = " ")
            binding.price.text = price
            binding.icon.setBackgroundColor(iconColor)
            binding.title.text = item.title

            binding.container.setOnClickListener{
                onClickToTheItem(item)
            }

        }
    }
}