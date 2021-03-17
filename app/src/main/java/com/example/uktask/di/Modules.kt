package com.example.uktask.di

import com.example.uktask.feature.weather.WeatherAdapter
import org.koin.dsl.module

val adaptersModule = module {
    factory { WeatherAdapter() }
}