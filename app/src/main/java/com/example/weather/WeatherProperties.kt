package com.example.weather

import com.squareup.moshi.Json

data class WeatherProperties(@Json(name = "data")val currentData: List<CurrentData>)

data class CurrentData( @Json(name = "weather")val weather: Weather, @Json(name = "temp")val temp: Double,@Json(name = "city_name") val city:String,@Json(name = "country_code") val country:String)

data class Weather(@Json(name = "description")val description: String,@Json(name = "icon")val icon:String)
