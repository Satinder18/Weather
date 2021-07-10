package com.example.weather.helper

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data")
data class WeatherDataClass(
    @PrimaryKey(autoGenerate = true)
    val weatherId:Long=0L,

    @ColumnInfo(name = "date")
    val date:String,

    @ColumnInfo(name = "Temperature")
    var Temperature:String,

    @ColumnInfo(name = "Description")
    var Description:String,

    @ColumnInfo(name = "weatherIcon")
    var weatherIcon:String
)