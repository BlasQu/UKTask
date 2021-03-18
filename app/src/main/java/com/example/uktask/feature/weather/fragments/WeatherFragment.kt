package com.example.uktask.feature.weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uktask.R
import com.example.uktask.data.models.Weather
import com.example.uktask.databinding.FragmentWeatherBinding
import com.example.uktask.feature.weather.WeatherActivity
import com.example.uktask.feature.weather.WeatherViewmodel
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.android.sharedViewModel

class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var weatherActivity: WeatherActivity
    private val adapter = WeatherAdapter { item -> weatherActivity.changeFragment(item) }
    private val viewmodel by sharedViewModel<WeatherViewmodel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherBinding.bind(view)
        weatherActivity = activity as WeatherActivity

        setupRV()
    }
    private fun setupRV() {
        val data = viewmodel.loadFromJson()
        data?.let {
            val weather = Gson().fromJson(data, Weather::class.java)
            binding.rvWeather.apply {
                layoutManager = LinearLayoutManager(weatherActivity)
                adapter = this@WeatherFragment.adapter
            }
            adapter.submitData(weather)
        }
    }
}