package com.example.uktask.feature.weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uktask.R
import com.example.uktask.databinding.FragmentCityBinding

class CityFragment : Fragment(R.layout.fragment_city) {
    private lateinit var binding: FragmentCityBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityBinding.bind(view)
    }
}