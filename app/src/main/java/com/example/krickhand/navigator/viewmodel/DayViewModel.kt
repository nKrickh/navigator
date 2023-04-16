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
import java.time.Instant
import java.util.*

class DayViewModel(
    private val repository: DayRepository,
    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    // List of Daytasks for a given day
    val data: LiveData<List<DayTaskDetail>> get() = _data
    private val _data = MutableLiveData<List<DayTaskDetail>>()
    val today: LiveData<Day> = repository.today.asLiveData()

    // For selected current task
    private val _daytask = MutableLiveData<DayTaskDetail>()
    val dayTask: LiveData<DayTaskDetail> get() = _daytask
    private val _tasktags = MutableLiveData<List<Tag>>()
    val taskTags: LiveData<List<Tag>> get() = _tasktags
    val currentTimestamp: LiveData<TimeStamp> get() = _currentTimeStamp
    private val _currentTimeStamp = MutableLiveData<TimeStamp>()

    val testStamp = TimeStamp()

    // Will load the default data for the current day
    init {
        loadData()
    }
    private fun loadData() {
        // co-routine scope with the lifecycle of the ViewModel
        viewModelScope.launch {
            repository.currentDayTaskList.collect {
                _data.value = it
            }
            // default timestamp - on initialization - TEST DATA
            //_currentTimeStamp.value = TimeStamp()
            clearTimestamp(testStamp)
            val now = Date.from(Instant.now())
            testStamp.apply {
                id = 1
                dId = 1
                tId = 1
                open = now.toString()
                close = now.time.plus(1).toString()
                lastEdit = this.close
            }
        }
    }

    fun setDayTask(taskId: Long) {
        viewModelScope.launch {
            _daytask.value = _data.value?.find { it -> it.tId == taskId }
            repository.getCurrentDayTaskTags(taskId)
            repository.currentDayTaskTags.collect {
                _tasktags.value = it
            }
        }
    }

    fun processTimestamp() {
        viewModelScope.launch {
            val now = Date.from(Instant.now()).toString()

            if (testStamp.open != "") {
                testStamp.close = now
                repository.addTimestamp(testStamp)
                clearTimestamp(testStamp)
            }

            else {
                testStamp.dId = dayTask.value!!.dId
                testStamp.tId = dayTask.value!!.tId
                testStamp.open = now
                testStamp.close = ""
            }
        }
    }
    private fun clearTimestamp(ts: TimeStamp) {
        ts.apply {
            dId = 0
            tId = 0
            open = ""
            close = ""
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

