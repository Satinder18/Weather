package com.example.weather.helper


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weather.MarsApiStatus
import com.example.weather.R


@BindingAdapter("date")
fun TextView.setTextViewFormatted(item:WeatherDataClass?){
    item?.let{
        text= item.date
    }
}


@BindingAdapter("Description")
fun TextView.Description(item:WeatherDataClass?) {
    item?.let {
        text = item.Description
    }
}


@BindingAdapter("Temprature")
fun TextView.Temprature(item:WeatherDataClass?) {
    item?.let {
        val temp= "${item.Temperature}°C"
        text = temp
    }
}

@BindingAdapter("weatherIcon")
fun ImageView.icon(item:WeatherDataClass?) {
    item?.let {
        val id= resources.getIdentifier(it.weatherIcon , "drawable", context?.packageName)
        setImageResource(id)
    }
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
