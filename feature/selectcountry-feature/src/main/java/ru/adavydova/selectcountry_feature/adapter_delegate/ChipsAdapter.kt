package ru.adavydova.selectcountry_feature.adapter_delegate

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.adavydova.selectcountry_feature.databinding.ChipsItemBinding

class ChipsAdapter(private val items: List<Chip>): RecyclerView.Adapter<ChipsAdapter.ChipsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipsViewHolder {
        val binding = ChipsItemBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
        return ChipsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChipsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ChipsViewHolder(private val binding: ChipsItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Chip){
            when(item.icon){
                null -> binding.icon.visibility = View.GONE
                else -> binding.icon.setImageResource(item.icon)
            }
            when(item.subTitle){
                null -> binding.title.text = item.title
                else -> {
                    val text = "${item.title}, ${item.subTitle}"
                    binding.title.text = text
                }
            }
        }
    }
}