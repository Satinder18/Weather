package com.example.weather

import android.app.Application
import androidx.lifecycle.*
import com.example.weather.helper.WeatherDao
import com.example.weather.helper.WeatherDataClass
import timber.log.Timber
import com.example.weather.helper.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelWeatherHistoryFragment(dataSource: WeatherDao, application: Application) : AndroidViewModel(application) {

    private var _status= MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status
    val weatherData=dataSource.getAllWeatherData()
    var repository: WeatherRepository=WeatherRepository(dataSource)
    init {
            getWeatherForecast()

    }

    private fun getWeatherForecast() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _status.value=MarsApiStatus.LOADING
                viewModelScope.launch(Dispatchers.IO) { repository.delete() }
                val getPropertiesDeferred = Weatherapi.retrofitService.getForecastProperties()
                val listResult = getPropertiesDeferred.await()
                listResult.currentData.forEach { forecast ->
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.insert(
                            WeatherDataClass(
                                date = forecast.date,
                                Temperature= "${forecast.temp}",
                                Description = forecast.weather.description,
                                weatherIcon = forecast.weather.weatherIcon)
                        )
                    }
                }
                _status.value=MarsApiStatus.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value=MarsApiStatus.ERROR
            }
        }
    }
}