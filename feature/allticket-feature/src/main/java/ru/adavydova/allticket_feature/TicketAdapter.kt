package ru.adavydova.allticket_feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.adavydova.allticket_feature.databinding.TicketItemBinding
import ru.adavydova.searchflights_data.models.Ticket
import ru.adavydova.ui_component.adapter_delegate.utils.getDiffBetweenTwoDate
import ru.adavydova.ui_component.adapter_delegate.utils.priceFormatter

class TicketAdapter(
    private val items: List<Ticket>
): RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val binding = TicketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }



    inner class TicketViewHolder(private val binding: TicketItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Ticket){
            if (item.badge != null){
                binding.badge.text = item.badge
            } else {
                binding.badge.visibility = View.INVISIBLE
            }

            binding.priceItem.text = item.price.value.priceFormatter()
            binding.airport1.text = item.departure.airport
            binding.airport2.text = item.arrival.airport

            val timeDeparture = item.departure.date.split('T')[1].substringBeforeLast(':')
            val timeArrival = item.arrival.date.split('T')[1].substringBeforeLast(':')
            val time = "$timeDeparture - $timeArrival"
            binding.time.text = time

            val hoursOnTheRoad = item.departure.date.getDiffBetweenTwoDate(item.arrival.date) + "ч. в пути"
            val transfer = when(item.hasTransfer){
                true -> ""
                false -> "/Без пересадок"
            }
            val result = "$hoursOnTheRoad $transfer"
            binding.hoursOnTheRoad.text = result
        }

    }
}