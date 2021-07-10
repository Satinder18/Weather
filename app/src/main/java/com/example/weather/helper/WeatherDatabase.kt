package com.example.weather.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

    @Database(entities = [WeatherDataClass::class],version = 1,exportSchema = false)
    abstract class WeatherDatabase : RoomDatabase(){

        abstract val weatherDao:WeatherDao

        companion object {
            @Volatile
            private var Instance: WeatherDatabase? = null

            fun getInstance(context: Context): WeatherDatabase {
                    synchronized(this) {

                    var instance = Instance
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            WeatherDatabase::class.java,
                            "weather_data")
                            .fallbackToDestructiveMigration()
                            .build()

                        Instance = instance

                    }
                    return instance
                }
            }

        }
    }
