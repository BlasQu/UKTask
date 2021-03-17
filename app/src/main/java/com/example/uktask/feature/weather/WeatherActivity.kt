package com.example.uktask.feature.weather

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.uktask.R
import com.example.uktask.databinding.ActivityWeatherBinding
import com.google.android.material.snackbar.Snackbar
import java.io.InputStream

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadJson(this, "data2.json")
    }

    private fun loadJson(context: Context, filename: String): String? {
        val jsonString: String?
        return try {
            jsonString = context.assets.open(filename).bufferedReader().readText()
            Log.d("Json", jsonString)
            jsonString
        } catch (e: Exception) {
            Log.d("JSON", e.stackTraceToString())
            Snackbar.make(binding.root, resources.getString(R.string.jsonError), Snackbar.LENGTH_SHORT).show()
            null
        }
    }
}