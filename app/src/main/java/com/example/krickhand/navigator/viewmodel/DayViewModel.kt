package com.example.krickhand.navigator.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.krickhand.navigator.NaviGatorApplication
import com.example.krickhand.navigator.dto.DayTaskListItem
import com.example.krickhand.navigator.entity.*
import com.example.krickhand.navigator.repo.DayRepository

class DayViewModel(
    repository: DayRepository,
    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    //val allDays: LiveData<List<Day>> = repository.allDays.asLiveData()
    val today: LiveData<Day> = repository.today.asLiveData()
    val daytasklist: LiveData<List<DayTaskListItem>> = repository.currentDayTaskList.asLiveData()

    // For the given selected current task
    private val mutableSelectedTask = MutableLiveData<TaskTag>()
//    val selectedTask: LiveData<TaskTag> get() = mutableSelectedTask

//    fun selectTask(task: Task) {
//        mutableSelectedTask.value = repository.getTask(task.taskId)
//    }
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
//    fun insert(day: Day) = viewModelScope.launch {
//        repository.insert(day)
//    }
    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                if (modelClass.isAssignableFrom(DayViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return DayViewModel(
                        (application as NaviGatorApplication).dayRepository,
                        savedStateHandle
                    ) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}

