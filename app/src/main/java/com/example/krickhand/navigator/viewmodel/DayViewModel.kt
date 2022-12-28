package com.example.krickhand.navigator.viewmodel

import androidx.lifecycle.*
import com.example.krickhand.navigator.entity.Day
import com.example.krickhand.navigator.entity.DayWithTasks
import com.example.krickhand.navigator.repo.DayRepository
import kotlinx.coroutines.launch

class DayViewModel(private val repository: DayRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allDays: LiveData<List<Day>> = repository.allDays.asLiveData()
    val today: LiveData<DayWithTasks> = repository.today.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
//    fun insert(day: Day) = viewModelScope.launch {
//        repository.insert(day)
//    }
}

class DayViewModelFactory(private val repository: DayRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DayViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DayViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}