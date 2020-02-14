package com.weatherapp.presentation.today.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weatherapp.R
import com.weatherapp.core.presentation.inflate
import com.weatherapp.presentation.model.WindData
import com.weatherapp.presentation.utils.getWindsRepresentation
import com.weatherapp.presentation.utils.icon
import kotlinx.android.synthetic.main.layout_item_wind.view.*


class WindsAdapter :
    ListAdapter<WindData, WindsAdapter.ViewHolder>(WindsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :

        RecyclerView.ViewHolder(parent.inflate(R.layout.layout_item_wind)) {

        fun bind(item: WindData) {
            itemView.windSpeed.text = item.speedmin?.let { min ->
                item.speedmax?.let { max ->
                    getWindsRepresentation(
                        itemView.context,
                        min,
                        max
                    )
                }
            }
            itemView.name.text = item.name
            itemView.windType.rotation = item.direction.icon().toFloat()
        }
    }
}

private class WindsDiffCallback : DiffUtil.ItemCallback<WindData>() {
    override fun areItemsTheSame(oldItem: WindData, newItem: WindData): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: WindData, newItem: WindData): Boolean =
        oldItem == newItem
}