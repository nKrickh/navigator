package com.example.krickhand.navigator.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.viewmodel.DayViewModel

@BindingAdapter(value = ["app:daytasklist", "app:viewmodel"], requireAll = true)
fun setDaytasklist(recyclerView: RecyclerView, daytasklist: LiveData<List<DayTaskDetail>>, vm: DayViewModel) {
    val adapter = getOrCreateAdapter(recyclerView, vm)
    adapter.updateTasks(daytasklist.value)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView, vm: DayViewModel): DayTaskListAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is DayTaskListAdapter) {
        recyclerView.adapter as DayTaskListAdapter
    } else {
        val adapter = DayTaskListAdapter(vm)
        recyclerView.adapter = adapter
        adapter
    }
}
