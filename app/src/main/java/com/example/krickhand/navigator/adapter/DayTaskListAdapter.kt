package com.example.krickhand.navigator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.*
import com.example.krickhand.navigator.databinding.RecyclerviewDaytaskBinding
import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.fragment.DayTaskListFragmentDirections

class DayTaskListAdapter(): ListAdapter<DayTaskDetail, DayTaskListAdapter.DayTaskViewHolder>(DayTaskDetailComparator()) {
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayTaskViewHolder {
        return DayTaskViewHolder.create(parent)
    }
    override fun onBindViewHolder(holder: DayTaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
    class DayTaskViewHolder(private val binding: RecyclerviewDaytaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DayTaskDetail?) {
            if (item != null) {
                binding.taskName.text = item.taskName
                binding.floatingActionButton.setOnClickListener {
                    val action = DayTaskListFragmentDirections.navigateToDaytaskDetail(item.tId)
                    it.findNavController().navigate(action)
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): DayTaskViewHolder {
                val binding = RecyclerviewDaytaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return DayTaskViewHolder(binding)
            }
        }
    }

    class DayTaskDetailComparator : DiffUtil.ItemCallback<DayTaskDetail>() {
        override fun areItemsTheSame(oldItem: DayTaskDetail, newItem: DayTaskDetail): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DayTaskDetail, newItem: DayTaskDetail): Boolean {
            return oldItem.tId == newItem.tId && oldItem.dId == newItem.dId
        }
    }
}