package com.example.krickhand.navigator.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.krickhand.navigator.databinding.ActivityDayBinding
import com.example.krickhand.navigator.databinding.ActivityNotesBinding

class Notes : AppCompatActivity() {

    private lateinit var _binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNotesBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
    }
}