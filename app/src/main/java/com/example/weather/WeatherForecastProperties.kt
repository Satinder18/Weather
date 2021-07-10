package com.example.weather

import com.squareup.moshi.Json

data class WeatherForecastProperties(@Json(name = "data")val currentData: List<ForecastData> )

data class ForecastData( @Json(name = "weather")val weather: ForecastWeather, @Json(name = "temp")val temp: Double,@Json(name = "datetime")val date:String)

data class ForecastWeather(@Json(name = "icon")val weatherIcon:String,@Json(name = "description")val description: String)
