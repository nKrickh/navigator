package com.example.krickhand.navigator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.krickhand.navigator.R
import com.example.krickhand.navigator.databinding.RecyclerviewItemBinding
import com.example.krickhand.navigator.entity.Day

class DayListAdapter: ListAdapter<Day, DayListAdapter.DayViewHolder>(DayComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class DayViewHolder(private val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(day: Day?) {
            with (binding) {
                if (day != null) {
                    binding.textView.text = day.dayLong
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): DayViewHolder {
                val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return DayViewHolder(binding)
            }
        }
    }

    class DayComparator : DiffUtil.ItemCallback<Day>() {
        override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem.dayId == newItem.dayId
        }
    }
}