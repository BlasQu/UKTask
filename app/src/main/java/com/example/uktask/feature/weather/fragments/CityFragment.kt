package com.example.uktask.feature.weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.example.uktask.R
import com.example.uktask.databinding.FragmentCityBinding
import com.example.uktask.feature.weather.WeatherActivity
import com.example.uktask.feature.weather.WeatherViewmodel
import com.example.uktask.util.CityImage
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CityFragment : Fragment(R.layout.fragment_city) {
    private lateinit var binding: FragmentCityBinding
    private lateinit var weatherActivity: WeatherActivity
    private val viewmodel by sharedViewModel<WeatherViewmodel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityBinding.bind(view)
        weatherActivity = activity as WeatherActivity

        setupLayout()
    }
    private fun setupLayout() {
        val item = viewmodel.currentCity.value
        item?.let {
            binding.textCity.text = item.city
            binding.imgCity.setImageDrawable(ResourcesCompat.getDrawable(resources, CityImage().cardImage(item.city), weatherActivity.theme))
        }
    }
}