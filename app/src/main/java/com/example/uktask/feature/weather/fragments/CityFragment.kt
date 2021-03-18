package com.example.uktask.feature.weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uktask.R
import com.example.uktask.databinding.FragmentCityBinding
import com.example.uktask.feature.weather.WeatherActivity
import com.example.uktask.feature.weather.WeatherViewmodel
import com.example.uktask.util.CityImage
import com.example.uktask.util.Divider
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CityFragment : Fragment(R.layout.fragment_city) {
    private lateinit var binding: FragmentCityBinding
    private lateinit var weatherActivity: WeatherActivity
    private val viewmodel by sharedViewModel<WeatherViewmodel>()
    private val adapter = get<CityTemperaturesAdapter>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityBinding.bind(view)
        weatherActivity = activity as WeatherActivity

        setupLayout()
        setupRV()
    }

    private fun setupRV() {
        val divideItemDecoration = Divider(weatherActivity)
        divideItemDecoration.setDrawable(resources.getDrawable(R.drawable.item_decoration, weatherActivity.theme))
        val data = viewmodel.currentCity.value?.hourly_temp
        data?.let {
            binding.rvTemperatures.apply {
                layoutManager = LinearLayoutManager(weatherActivity)
                adapter = this@CityFragment.adapter
                addItemDecoration(divideItemDecoration)
            }
            adapter.submitData(data)
        }
    }

    private fun setupLayout() {
        val item = viewmodel.currentCity.value
        item?.let {
            binding.textCity.text = item.city
            binding.imgCity.setImageDrawable(ResourcesCompat.getDrawable(resources, CityImage().cardImage(item.city), weatherActivity.theme))
            binding.imgIcon.setImageDrawable(ResourcesCompat.getDrawable(resources, CityImage().iconImage(item.weather), weatherActivity.theme))
        }
    }
}