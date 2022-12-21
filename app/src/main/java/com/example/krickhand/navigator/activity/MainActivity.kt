package com.example.krickhand.navigator.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krickhand.navigator.NaviGatorApplication
import com.example.krickhand.navigator.adapter.DayListAdapter
import com.example.krickhand.navigator.databinding.ActivityMainBinding
import com.example.krickhand.navigator.viewmodel.DayViewModel
import com.example.krickhand.navigator.viewmodel.DayViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //private val newWordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }


}