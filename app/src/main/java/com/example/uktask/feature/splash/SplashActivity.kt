package com.example.uktask.feature.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.lifecycle.lifecycleScope
import com.example.uktask.R
import com.example.uktask.databinding.ActivitySplashBinding
import com.example.uktask.feature.weather.WeatherActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animateSplash()
        changeActivity()
    }

    private fun animateSplash() {
        val shortAnimTime = resources.getInteger(R.integer.shortAnim).toLong()
        binding.root.animate().apply {
            alpha(1f)
            interpolator = AccelerateDecelerateInterpolator()
            duration = shortAnimTime
        }
    }

    private fun changeActivity() {
        val intent = Intent(this, WeatherActivity::class.java)
        lifecycleScope.launch {
            delay(3000)
            startActivity(intent)
            finish()
        }
    }
}