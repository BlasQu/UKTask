package com.example.uktask.di

import com.example.uktask.data.models.WeatherItem
import com.example.uktask.feature.weather.WeatherRepository
import com.example.uktask.feature.weather.WeatherViewmodel
import com.example.uktask.feature.weather.fragments.CityFragment
import com.example.uktask.feature.weather.fragments.CityTemperaturesAdapter
import com.example.uktask.feature.weather.fragments.WeatherAdapter
import com.example.uktask.feature.weather.fragments.WeatherFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val adaptersModule = module {
    factory { (click: (WeatherItem) -> Unit) -> WeatherAdapter(click) }
    factory { CityTemperaturesAdapter() }
}

val fragmentsModule = module {
    factory { WeatherFragment() }
    factory { CityFragment() }
}

val architectureModule = module {
    single { WeatherRepository(androidContext()) }
    viewModel { WeatherViewmodel(get()) }
}