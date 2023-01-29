package com.example.krickhand.navigator.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.krickhand.navigator.databinding.ActivityDayBinding
import com.example.krickhand.navigator.databinding.ActivityJournalBinding

class Journal : AppCompatActivity() {

    private lateinit var _binding: ActivityJournalBinding

    // Lifecycle hooks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityJournalBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
    }
}