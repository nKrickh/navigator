package com.example.krickhand.navigator.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.entity.Tag
import com.example.krickhand.navigator.viewmodel.DayViewModel

@BindingAdapter(value = ["daytasklist", "viewmodel"])
fun setDaytasklist(recyclerView: RecyclerView, daytasklist: LiveData<List<DayTaskDetail>>, vm: DayViewModel) {
    val adapter = getOrCreateDayTaskAdapter(recyclerView, vm)
    adapter.updateTasks(daytasklist.value)
}

@BindingAdapter(value = ["taglist", "viewmodel"])
fun setTaglist(recyclerView: RecyclerView, taglist: LiveData<List<Tag>>, vm: DayViewModel) {
    val adapter = getOrCreateTagAdapter(recyclerView, vm)
    adapter.updateTags(taglist.value)
}
private fun getOrCreateDayTaskAdapter(recyclerView: RecyclerView, vm: DayViewModel): DayTaskListAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is DayTaskListAdapter) {
        recyclerView.adapter as DayTaskListAdapter
    } else {
        val adapter = DayTaskListAdapter(vm)
        recyclerView.adapter = adapter
        adapter
    }
}

private fun getOrCreateTagAdapter(recyclerView: RecyclerView, vm: DayViewModel): TagListAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is TagListAdapter) {
        recyclerView.adapter as TagListAdapter
    } else {
        val adapter = TagListAdapter(vm)
        recyclerView.adapter = adapter
        adapter
    }
}
