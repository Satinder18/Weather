package com.example.weather


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.weatherbit.io/v2.0/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    //.addConverterFactory(ScalarsConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService{
    @GET("current")
    fun getProperties(@Query("city_id") city: String= "1278149",
                      @Query("key") key:String="bfbc33d7e1d442d1971fc7862e61c22d"): Deferred<WeatherProperties>

    @GET("forecast/daily")
    fun getForecastProperties(@Query("city_id") city: String= "1278149",
                      @Query("key") key:String="bfbc33d7e1d442d1971fc7862e61c22d"): Deferred<WeatherForecastProperties>
}
object Weatherapi {
    val retrofitService : WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}