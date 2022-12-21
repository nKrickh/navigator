package com.example.krickhand.navigator.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krickhand.navigator.NaviGatorApplication
import com.example.krickhand.navigator.adapter.DayListAdapter
import com.example.krickhand.navigator.databinding.ActivityYearBinding
import com.example.krickhand.navigator.viewmodel.DayViewModel
import com.example.krickhand.navigator.viewmodel.DayViewModelFactory

class YearActivity : AppCompatActivity() {

    private val dayViewModel: DayViewModel by viewModels {
        DayViewModelFactory((application as NaviGatorApplication).repository)
    }

    private lateinit var binding: ActivityYearBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYearBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = DayListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        dayViewModel.allDays.observe(this) { days ->
            // Update the cached copy of the words in the adapter.
            days.let { adapter.submitList(it) }
        }
    }
}