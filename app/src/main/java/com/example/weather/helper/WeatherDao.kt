package com.example.weather.helper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao{

    @Insert
    fun insertWeatherData(weather:WeatherDataClass)

    @Query("SELECT * FROM weather_data ORDER by weatherId ASC")
    fun getAllWeatherData(): LiveData<List<WeatherDataClass>>

    @Query("DELETE FROM weather_data")
    fun clearData()

}


