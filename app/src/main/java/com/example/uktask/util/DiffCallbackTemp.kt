package com.example.uktask.util

import androidx.recyclerview.widget.DiffUtil
import com.example.uktask.data.models.HourlyTemp
import com.example.uktask.data.models.WeatherItem

class DiffCallbackTemp(val oldList: List<HourlyTemp>, val newList: List<HourlyTemp>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].hour == newList[newItemPosition].hour
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}