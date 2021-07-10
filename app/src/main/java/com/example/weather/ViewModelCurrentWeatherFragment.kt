package com.example.weather

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

enum class MarsApiStatus{LOADING,ERROR,DONE}
class ViewModelCurrentWeatherFragment:ViewModel() {


    var loading = MutableLiveData<Boolean>()
    var done = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()


    private val viewModelJob = Job()
    private val coroutineScope= CoroutineScope(viewModelJob+ Dispatchers.Main)

    // The external immutable LiveData for the request status String


    private val _property= MutableLiveData<WeatherProperties>()
    val property: LiveData<WeatherProperties>
        get() = _property

    private var _status=MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status


    private var _temp=MutableLiveData<String>()
    val temp: LiveData<String>
        get() = _temp

    private var _location=MutableLiveData<String>()
    val location: LiveData<String>
        get() = _location

    private var _icon=MutableLiveData<String>()
    val icon: LiveData<String>
        get() = _icon

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getWeatherProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getWeatherProperties() {
        coroutineScope.launch {
            try {
                _status.value=MarsApiStatus.LOADING
                val getPropertiesDeferred = Weatherapi.retrofitService.getProperties()
                val listResult = getPropertiesDeferred.await()
                   _property.value=listResult
                   _temp.value="${listResult.currentData[0].temp}°C"
                _location.value="${listResult.currentData[0].city},${listResult.currentData[0].country}"
                _icon.value=listResult.currentData[0].weather.icon
                _status.value=MarsApiStatus.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value=MarsApiStatus.ERROR
            }
        }
    }
}