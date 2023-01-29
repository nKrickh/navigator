package com.example.krickhand.navigator.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.krickhand.navigator.databinding.ActivityDayBinding



class Day : AppCompatActivity() {

    private lateinit var _binding: ActivityDayBinding

    // Lifecycle hooks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDayBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
    }
}