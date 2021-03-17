package com.example.uktask.feature.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.uktask.data.models.WeatherItem
import com.example.uktask.databinding.ItemWeatherBinding
import com.example.uktask.util.DiffCallback

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private val data: MutableList<WeatherItem> = mutableListOf()
    inner class ViewHolder(val binding: ItemWeatherBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder {
        return ViewHolder(ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        holder.binding.textCity.text = data[position].city
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