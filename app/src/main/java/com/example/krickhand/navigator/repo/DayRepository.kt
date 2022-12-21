package com.example.krickhand.navigator.repo

import androidx.annotation.WorkerThread
import com.example.krickhand.navigator.dao.DayDao
import com.example.krickhand.navigator.entity.Day
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class DayRepository(private val dayDao: DayDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allDays: Flow<List<Day>> = dayDao.getAlphabetizedDays()
    val today: Flow<Day> = dayDao.getDay(0)

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(day: Day) {
        dayDao.insertDay(day)
    }
}