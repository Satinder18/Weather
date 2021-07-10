package com.example.weather

import android.os.Bundle
import android.app.ActionBar
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.FragmentWeatherHistoryBinding
import com.example.weather.helper.WeatherDatabase
import com.example.weather.helper.weatherHistoryAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class WeatherHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentWeatherHistoryBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_weather_history,container,false)
        val application= requireNotNull(this.activity).application
        val dataSource=WeatherDatabase.getInstance(application).weatherDao
        val viewModelFactory =ViewModelFactoryWeatherHistoryFragment(dataSource,application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(ViewModelWeatherHistoryFragment::class.java)

        binding.lifecycleOwner = this
        binding.viewModelWeatherHistoryFragment=viewModel

        activity?.title="Weather History"

        val adapter=weatherHistoryAdapter()
        binding.weatherList.adapter=adapter

        binding.weatherHistoryButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_weatherHistoryFragment_to_currentWeatherFragment2)
        )

            viewModel.weatherData.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
            })


        return binding.root
    }
}
