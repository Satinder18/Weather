package com.example.weather.helper

import androidx.lifecycle.LiveData

class WeatherRepository(private val weatherDao: WeatherDao) {

    val weatherForecast= weatherDao.getAllWeatherData()

    suspend fun insert(weather: WeatherDataClass) {
        weatherDao.insertWeatherData(weather)
    }

    suspend fun delete(){
        weatherDao.clearData()
    }


}