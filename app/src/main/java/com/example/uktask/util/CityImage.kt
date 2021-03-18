package com.example.uktask.util

import com.example.uktask.R

class CityImage {
    fun cardImage(city: String): Int {
        return when (city) {
            "Warsaw" -> R.drawable.warsaw
            "Berlin" -> R.drawable.berlin
            "New York" -> R.drawable.ny
            "Paris" -> R.drawable.paris
            else -> R.drawable.noimg
        }
    }
}