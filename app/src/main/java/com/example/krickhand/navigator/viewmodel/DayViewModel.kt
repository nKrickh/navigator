package com.example.krickhand.navigator.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.krickhand.navigator.NaviGatorApplication
import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.entity.*
import com.example.krickhand.navigator.repo.DayRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DayViewModel(
    private val repository: DayRepository,
    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val data: LiveData<List<DayTaskDetail>> get() = _data
    private val _data = MutableLiveData<List<DayTaskDetail>>()

    val currentOpenTimeStamp: LiveData<TimeStamp> get() = _currentOpenTimeStamp
    private val _currentOpenTimeStamp = MutableLiveData<TimeStamp>()

    init {
        loadData()
    }
    private fun loadData() {
        // co-routine scope with the lifecycle of the ViewModel
        viewModelScope.launch {
            repository.currentDayTaskList.collect {
                _data.value = it
            }
        }
    }


    val today: LiveData<Day> = repository.today.asLiveData()
    //val daytasklist: LiveData<List<DayTaskDetail>> = repository.currentDayTaskList(tempId).asLiveData()

    // For the given selected current task
    private val _daytask = MutableLiveData<DayTaskDetail>()
    val dayTask: LiveData<DayTaskDetail> get() = _daytask
    private val _tasktags = MutableLiveData<List<Tag>>()
    val taskTags: LiveData<List<Tag>> get() = _tasktags

    fun setDayTask(taskId: Long) {
        viewModelScope.launch {
            _daytask.value = _data.value?.find { it -> it.tId == taskId }
            repository.getCurrentDayTaskTags(taskId)
            repository.currentDayTaskTags.collect {
                _tasktags.value = it
            }
        }
    }

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

