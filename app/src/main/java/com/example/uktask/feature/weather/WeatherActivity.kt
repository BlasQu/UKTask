package com.example.uktask.feature.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.uktask.R
import com.example.uktask.data.models.Temperature
import com.example.uktask.data.models.Weather
import com.example.uktask.data.models.WeatherItem
import com.example.uktask.databinding.ActivityWeatherBinding
import com.example.uktask.feature.weather.fragments.CityFragment
import com.example.uktask.feature.weather.fragments.WeatherFragment
import com.google.gson.Gson
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import java.math.BigDecimal
import java.math.RoundingMode

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private val weatherFragment = get<WeatherFragment>()
    private val cityFragment = get<CityFragment>()
    private val viewmodel by viewModel<WeatherViewmodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFragments()
        viewmodel.temperatures() // Showing task results (temperatures) in Console - You can search with ,,Zadania'' in Debug
    }
    private fun setupFragments() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, weatherFragment)
            addToBackStack("WeatherFragment")
            commit()
        }
    }
    fun changeFragment(item: WeatherItem) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, cityFragment)
            addToBackStack("CityFragment")
            commit()
        }
        viewmodel.currentCity.value = item
    }
}