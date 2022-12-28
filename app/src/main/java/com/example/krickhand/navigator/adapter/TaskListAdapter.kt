package com.example.krickhand.navigator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.krickhand.navigator.databinding.RecyclerviewTaskBinding
import com.example.krickhand.navigator.entity.Task

class TaskListAdapter: ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class TaskViewHolder(private val binding: RecyclerviewTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task?) {
            if (task != null) {
                binding.taskName.text = task.name
            }
        }

        companion object {
            fun create(parent: ViewGroup): TaskViewHolder {
                val binding = RecyclerviewTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return TaskViewHolder(binding)
            }
        }
    }

    class TaskComparator : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.taskId == newItem.taskId
        }
    }
}