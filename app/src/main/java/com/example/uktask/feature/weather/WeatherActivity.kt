package com.example.uktask.feature.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uktask.R
import com.example.uktask.data.models.WeatherItem
import com.example.uktask.databinding.ActivityWeatherBinding
import com.example.uktask.feature.weather.fragments.CityFragment
import com.example.uktask.feature.weather.fragments.WeatherFragment
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel

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
        setupToolbar()
        viewmodel.temperatures() // Showing task results (temperatures) in Console - You can search with ,,Zadania'' in Debug
    }
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarLayout.root)
        supportActionBar?.apply {
            title = "Cities"
            setDisplayHomeAsUpEnabled(false)
        }
    }
    private fun setupFragments() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, weatherFragment)
            addToBackStack("WeatherFragment")
            commit()
        }
    }
    fun changeFragment(item: WeatherItem) {
        when (getLastFragmentName()) {
            "WeatherFragment" -> {
                viewmodel.currentCity.value = item
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, cityFragment)
                    addToBackStack("CityFragment")
                    commit()
                }
                supportActionBar?.apply {
                    title = "Weather in ${item.city}"
                    setDisplayHomeAsUpEnabled(true)
                }
            }
            "CityFragment" -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, weatherFragment)
                    addToBackStack("WeatherFragment")
                    commit()
                }
                supportActionBar?.apply {
                    title = "Cities"
                    setDisplayHomeAsUpEnabled(false)
                }
            }
        }
    }
    private fun getLastFragmentName(): String {
        val entries = supportFragmentManager.backStackEntryCount
        return supportFragmentManager.getBackStackEntryAt(entries-1).name!!
    }

    override fun onBackPressed() {
        if(getLastFragmentName() == "CityFragment") {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, weatherFragment)
                addToBackStack("WeatherFragment")
                commit()
            }
            supportActionBar?.apply {
                title = "Cities"
                setDisplayHomeAsUpEnabled(false)
            }
        } else finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}