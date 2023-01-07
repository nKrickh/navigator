package com.example.krickhand.navigator.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.krickhand.navigator.databinding.ActivityDayBinding

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
        setContentView(view)


        //val testId = 0;



    }
}