package com.example.krickhand.navigator.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krickhand.navigator.NaviGatorApplication
import com.example.krickhand.navigator.adapter.TaskListAdapter
import com.example.krickhand.navigator.databinding.ActivityDayBinding
import com.example.krickhand.navigator.viewmodel.DayViewModel
import com.example.krickhand.navigator.viewmodel.DayViewModelFactory

class Day : AppCompatActivity() {

    private lateinit var binding: ActivityDayBinding
    private val dayViewModel: DayViewModel by viewModels {
        DayViewModelFactory((application as NaviGatorApplication).dayRepository)
    }

    // Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = TaskListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

//        dayViewModel.today.observe(this) { days ->
//            // Update the cached copy of the words in the adapter.
//            days.let { adapter.submitList(it) }
//        }

        //val testId = 0;
        dayViewModel.today.observe(this) { dayWithTasks ->
            if (dayWithTasks != null) {
                binding.dayTitle.text = dayWithTasks.day.dayLong
                dayWithTasks.let {adapter.submitList(dayWithTasks.tasks)}
            }
        }

    }
}