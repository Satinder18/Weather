package com.example.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.weather.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private val data = Data("Weather")
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.WeatherText.text = data.Weathertext

        binding.currentWeatherbutton.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_currentWeatherFragment))

        binding.weatherHistorybutton.setOnClickListener  (
            Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_weatherHistoryFragment))

        return binding.root
    }
}