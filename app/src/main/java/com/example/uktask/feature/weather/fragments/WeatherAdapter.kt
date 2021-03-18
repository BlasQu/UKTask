package com.example.uktask.feature.weather.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.uktask.R
import com.example.uktask.data.models.WeatherItem
import com.example.uktask.databinding.ItemWeatherBinding
import com.example.uktask.util.CityImage
import com.example.uktask.util.DiffCallback

class WeatherAdapter(
    val onClick: (WeatherItem) -> Unit
): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private val data: MutableList<WeatherItem> = mutableListOf()
    inner class ViewHolder(val binding: ItemWeatherBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { onClick(data[adapterPosition]) }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textCity.text = data[position].city
        holder.binding.cardBgImage.setImageDrawable(ResourcesCompat.getDrawable(holder.itemView.context.resources, CityImage().cardImage(data[position].city), holder.itemView.context.theme))
    }
    override fun getItemCount(): Int {
        return data.size
    }
    fun submitData(newList: List<WeatherItem>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(data, newList))
        data.apply {
            clear()
            addAll(newList)
        }
        diffResult.dispatchUpdatesTo(this)
    }
}