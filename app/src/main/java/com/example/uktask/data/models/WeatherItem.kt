package com.example.uktask.data.models

data class WeatherItem(
    val city: String,
    val hourly_temp: List<HourlyTemp>,
    val weather: String
)