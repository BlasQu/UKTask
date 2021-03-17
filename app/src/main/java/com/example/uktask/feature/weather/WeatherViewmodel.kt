package com.example.uktask.feature.weather

import androidx.lifecycle.ViewModel

class WeatherViewmodel(
    val repository: WeatherRepository
): ViewModel() {
    fun loadFromJson(): String? {
        return repository.loadFromJson("data.json")
    }

    fun temperatures() {
        repository.temperatures()
    }
}