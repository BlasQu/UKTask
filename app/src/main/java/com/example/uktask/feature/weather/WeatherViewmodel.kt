package com.example.uktask.feature.weather

import androidx.lifecycle.ViewModel
import com.example.uktask.data.models.WeatherItem
import kotlinx.coroutines.flow.MutableStateFlow

class WeatherViewmodel(
    val repository: WeatherRepository
): ViewModel() {

    val currentCity = MutableStateFlow<WeatherItem?>(null)

    fun loadFromJson(): String? {
        return repository.loadFromJson("data.json")
    }

    fun temperatures() {
        repository.temperatures()
    }
}