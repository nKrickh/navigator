package com.example.krickhand.navigator.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.krickhand.navigator.NaviGatorApplication
import com.example.krickhand.navigator.databinding.ActivityDayBinding
import com.example.krickhand.navigator.viewmodel.DayViewModel


class Day : AppCompatActivity() {

    private lateinit var binding: ActivityDayBinding
//    private val dayViewModel: DayViewModel by viewModels {
//        DayViewModelFactory((application as NaviGatorApplication).dayRepository)
//    }

    // Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        val view = binding.root

        //val dayViewModel: DayViewModel by viewModels()
        setContentView(view)


        //val testId = 0;



    }
}