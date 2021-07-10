package com.example.weather

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.helper.WeatherDao

class ViewModelFactoryWeatherHistoryFragment (
    private val dataSource: WeatherDao,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewModelWeatherHistoryFragment::class.java)) {
                return ViewModelWeatherHistoryFragment(dataSource, application) as T
            }else
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}