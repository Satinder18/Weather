package com.example.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.weather.databinding.FragmentCurrentWeatherBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class CurrentWeatherFragment : Fragment() {
    private lateinit var viewModel:ViewModelCurrentWeatherFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel= ViewModelProvider(this).get(ViewModelCurrentWeatherFragment::class.java)

        // Inflate the layout for this fragment
        val binding:FragmentCurrentWeatherBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_current_weather, container, false)
        activity?.title="Current Weather"

        binding.currentWeatherButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_currentWeatherFragment_to_weatherHistoryFragment2)
        )
        binding.lifecycleOwner = this
        binding.currentViewModel=viewModel

        viewModel.icon.observe(viewLifecycleOwner, Observer {
            val id= resources.getIdentifier(it , "drawable", context?.packageName)
            binding.iconView.setImageResource(id)
        })

        return binding.root
    }

}
