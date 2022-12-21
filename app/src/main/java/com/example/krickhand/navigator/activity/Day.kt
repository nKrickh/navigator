package com.example.krickhand.navigator.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.krickhand.navigator.NaviGatorApplication
import com.example.krickhand.navigator.databinding.ActivityDayBinding
import com.example.krickhand.navigator.viewmodel.DayViewModel
import com.example.krickhand.navigator.viewmodel.DayViewModelFactory

class Day : AppCompatActivity() {

    private lateinit var binding: ActivityDayBinding
    private val dayViewModel: DayViewModel by viewModels {
        DayViewModelFactory((application as NaviGatorApplication).repository)
    }

    // Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //val testId = 0;
        dayViewModel.today.observe(this) { day ->
            binding.dayTitle.text = day.dayLong
        }

    }
}