package com.example.krickhand.navigator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.krickhand.navigator.databinding.RecyclerviewDaytaskBinding
import com.example.krickhand.navigator.dto.JournalEntryListItem

class JournalEntryListAdapter(): ListAdapter<JournalEntryListItem, JournalEntryListAdapter.JournalEntryViewHolder>(JournalEntryComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalEntryViewHolder {
        return JournalEntryViewHolder.create(parent)
    }
    override fun onBindViewHolder(holder: JournalEntryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
    class JournalEntryViewHolder(private val binding: RecyclerviewDaytaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: JournalEntryListItem?) {
            if (item != null) {
                //binding.taskName.text = item..taskName
//                binding.floatingActionButton.setOnClickListener {
//                    val action = JournalEntryListFragmentDirections.navigateToDaytaskDetail(item.tId)
//                    it.findNavController().navigate(action)
//                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): JournalEntryViewHolder {
                val binding = RecyclerviewDaytaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return JournalEntryViewHolder(binding)
            }
        }
    }

    class JournalEntryComparator : DiffUtil.ItemCallback<JournalEntryListItem>() {
        override fun areItemsTheSame(oldItem: JournalEntryListItem, newItem: JournalEntryListItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: JournalEntryListItem, newItem: JournalEntryListItem): Boolean {
            return oldItem.pId == newItem.pId && oldItem.jId == newItem.jId
        }
    }
}