package com.example.krickhand.navigator.repo

import com.example.krickhand.navigator.dao.DayDao
import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.entity.Status
import com.example.krickhand.navigator.entity.Tag
import com.example.krickhand.navigator.entity.TaskTag
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class DayRepository(private val dayDao: DayDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    //val allDays: Flow<List<Day>> = dayDao.getAlphabetizedDays()
    // val todayId = LocalDate.now().dayOfYear.toLong()

    private val tempId = 1L
    val today = dayDao.getDay(tempId)
    val currentDayTaskList: Flow<List<DayTaskDetail>> = dayDao.loadDayTaskList(tempId)
    //val currentDayTaskTags: Flow<List<TaskTag>> = dayDao.loadTaskTags(tempId)
    lateinit var currentDayTaskTags: Flow<List<Tag>>


    fun getCurrentDayTaskTags(id: Long) {
        currentDayTaskTags = dayDao.loadTaskTags(id)
    }


}
    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun insert(day: Day) {
//        dayDao.insertDay(day)
//    }
