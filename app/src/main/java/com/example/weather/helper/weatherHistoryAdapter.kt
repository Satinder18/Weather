package com.example.weather.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ListItemLayoutBinding

class weatherHistoryAdapter: ListAdapter<WeatherDataClass, weatherHistoryAdapter.viewHolder>(WeatherDataClassDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class viewHolder private constructor(val binding: ListItemLayoutBinding):RecyclerView.ViewHolder(binding.root){

        companion object{
            fun from(parent: ViewGroup):viewHolder{
                val layoutInflater=LayoutInflater.from(parent.context)
                val binding= ListItemLayoutBinding.inflate(layoutInflater,parent,false)
                return viewHolder(binding = binding)
            }
        }

        fun bind(item:WeatherDataClass){
                binding.weather=item
                binding.executePendingBindings()
        }
    }

    class WeatherDataClassDiffCallBack: DiffUtil.ItemCallback<WeatherDataClass>(){
        override fun areItemsTheSame(oldItem: WeatherDataClass, newItem: WeatherDataClass): Boolean {
            return oldItem.date==newItem.date
        }

        override fun areContentsTheSame(oldItem: WeatherDataClass, newItem: WeatherDataClass): Boolean {
            return oldItem==newItem
        }


    }
}