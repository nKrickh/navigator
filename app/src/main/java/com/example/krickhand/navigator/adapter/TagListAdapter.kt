package com.example.krickhand.navigator.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.krickhand.navigator.R
import com.example.krickhand.navigator.databinding.RecyclerviewTagBinding
import com.example.krickhand.navigator.databinding.RecyclerviewTaskBinding
import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.entity.Tag
import com.example.krickhand.navigator.fragment.DayTaskListFragmentDirections
import com.example.krickhand.navigator.viewmodel.DayViewModel

class TagListAdapter(private val dayViewModel: DayViewModel): RecyclerView.Adapter<TagViewHolder>() {

    private var taglist: List<Tag> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding: RecyclerviewTagBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_tag,
            parent,
            false
        )
        return TagViewHolder(binding, dayViewModel)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(taglist[position])
    }

    override fun getItemCount(): Int = taglist.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateTags(tags: List<Tag>?) {
        taglist = tags ?: emptyList()
        notifyDataSetChanged()
    }
}

class TagViewHolder(private val binding: RecyclerviewTagBinding, private val vm: DayViewModel) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Tag) {
        binding.tag = item
    }
}