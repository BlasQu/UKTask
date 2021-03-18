package com.example.uktask.feature.weather.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.uktask.data.models.HourlyTemp
import com.example.uktask.databinding.ItemTemperatureBinding
import com.example.uktask.util.DiffCallbackTemp

class CityTemperaturesAdapter: RecyclerView.Adapter<CityTemperaturesAdapter.ViewHolder>() {
    private val data: MutableList<HourlyTemp> = mutableListOf()
    inner class ViewHolder(val binding: ItemTemperatureBinding): RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTemperatureBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textHourtemp.text = "${data[position].hour.toInt()}:00 - (${data[position].temp})°C"
    }
    override fun getItemCount(): Int {
        return data.size
    }
    fun submitData(newList: List<HourlyTemp>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallbackTemp(data, newList))
        data.apply {
            clear()
            addAll(newList)
        }
        diffResult.dispatchUpdatesTo(this)
    }
}