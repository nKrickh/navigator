package com.example.krickhand.navigator.repo

import com.example.krickhand.navigator.dao.DayDao
import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.dto.DayTaskListItem
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
    val currentDayTaskList: Flow<List<DayTaskListItem>> = dayDao.loadDayTaskList(tempId)
    //private var _currentDayTaskDetail: Flow<DayTaskDetail>

    fun getCurrentDayTaskDetail(id: Long): DayTaskDetail {
//        val currentDayTaskDetail = dayDao.loadDayTaskDetails(id)
        return dayDao.loadDayTaskDetails(id)
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
