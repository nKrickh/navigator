package com.example.krickhand.navigator.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.*

import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.fragment.DayTaskListFragmentDirections
import com.example.krickhand.navigator.viewmodel.DayViewModel
import com.example.krickhand.navigator.R
import com.example.krickhand.navigator.databinding.RecyclerviewTaskBinding

class DayTaskListAdapter(private val dayViewModel: DayViewModel): RecyclerView.Adapter<DayTaskViewHolder>() {

    private var daytasklist: List<DayTaskDetail> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayTaskViewHolder {
        val binding: RecyclerviewTaskBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_task,
            parent,
            false
        )
        return DayTaskViewHolder(binding, dayViewModel)
    }

    override fun onBindViewHolder(holder: DayTaskViewHolder, position: Int) {
        holder.bind(daytasklist[position])
    }

    override fun getItemCount(): Int = daytasklist.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateTasks(daytasks: List<DayTaskDetail>?) {
        daytasklist = daytasks ?: emptyList()
        notifyDataSetChanged()
    }
}

class DayTaskViewHolder(private val binding: RecyclerviewTaskBinding, private val vm: DayViewModel) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DayTaskDetail) {
        binding.dayTaskDetail = item
        binding.dtaskDetailFAB.setOnClickListener {
            vm.setDayTask(item.tId)
            val action = DayTaskListFragmentDirections.navigateToDaytaskDetail()
            it.findNavController().navigate(action)
        }
    }
}