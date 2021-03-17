package com.example.uktask.util

import androidx.recyclerview.widget.DiffUtil
import com.example.uktask.data.models.WeatherItem

class DiffCallback(val oldList: List<WeatherItem>, val newList: List<WeatherItem>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].city == newList[newItemPosition].city
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}