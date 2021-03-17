package com.example.uktask.feature.weather

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uktask.R
import com.example.uktask.data.models.Temperature
import com.example.uktask.data.models.Weather
import com.example.uktask.databinding.ActivityWeatherBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.koin.android.ext.android.get
import java.lang.Math.floor
import java.math.BigDecimal
import java.math.RoundingMode

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    private val adapter = get<WeatherAdapter>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()
        answers()
    }

    private fun setupRV() {
        val data = loadFromJson(this, "data.json")
        data?.let {
            val weather = Gson().fromJson(data, Weather::class.java)
            binding.rvWeather.apply {
                layoutManager = LinearLayoutManager(this@WeatherActivity)
                adapter = this@WeatherActivity.adapter
            }
            adapter.submitData(weather)
        }
    }

    private fun loadFromJson(context: Context, filename: String): String? {
        val jsonString: String?
        return try {
            jsonString = context.assets.open(filename).bufferedReader().readText()
            Log.d("Json", jsonString)
            jsonString
        } catch (e: Exception) {
            Log.d("JSON", "Exception:", e)
            Snackbar.make(binding.root, resources.getString(R.string.jsonError), Snackbar.LENGTH_SHORT).show()
            null
        }
    }

    private fun answers() {
        val data = loadFromJson(this, "data.json")
        data?.let {
            val weather = Gson().fromJson(data, Weather::class.java)
            val avgTemps = mutableListOf<Temperature>()
            val minTemps = mutableListOf<Temperature>()
            weather.forEach { y ->
                var sumOfTemps = 0.0
                var count = 0
                val maxTempInCity = y.hourly_temp.maxByOrNull {
                    it.temp
                }
                val minTempInCity = y.hourly_temp.minByOrNull {
                    it.temp
                }
                y.hourly_temp.forEach {
                    sumOfTemps += it.temp
                    count++
                }
                val avgTemp = sumOfTemps / count
                val averageTempInCity = BigDecimal(avgTemp).setScale(2, RoundingMode.UP)
                avgTemps.add(Temperature(y.city, averageTempInCity))
                minTemps.add(Temperature(y.city, BigDecimal(minTempInCity!!.temp)))
                Log.d(
                    "Zadania",
                    "The highest noted air temperature during a day in ${y.city}: ${maxTempInCity!!.temp} Celsius degrees"
                )
            }
            val minAverageTemp = avgTemps.minByOrNull {
                it.temperature
            }
            val minTempAcrossAllCities = minTemps.minByOrNull {
                it.temperature
            }
            Log.d(
                "Zadania",
                "The lowest average air temperature occurs in ${minAverageTemp!!.city}: ${minAverageTemp.temperature} Celsius degrees."
            )
            Log.d(
                "Zadania",
                "The lowest noted air temperature across all cities occurs in ${minTempAcrossAllCities!!.city}: ${minTempAcrossAllCities.temperature} Celsius degrees."
            )
        }
    }
}