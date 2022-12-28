package com.example.krickhand.navigator.dao

import androidx.room.*
import com.example.krickhand.navigator.entity.Day
import com.example.krickhand.navigator.entity.DayTaskCrossRef
import com.example.krickhand.navigator.entity.DayWithTasks
//import com.example.krickhand.navigator.entity.DayWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDao {
    @Query("SELECT * FROM days ORDER BY dayShort ASC")
    fun getAlphabetizedDays(): Flow<List<Day>>

    // TO-DO: Why won't suspend with in repo?
    @Query("SELECT * FROM days WHERE dayId = :id")
    fun getDay(id: Long): Flow<Day>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDay(day: Day)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDays(days: List<Day>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayTasks(days: List<DayTaskCrossRef>)

    @Query("DELETE FROM days")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM days WHERE dayId = :id")
    fun getDayWithTasks(id: Long): Flow<DayWithTasks>

//    @Transaction
//    @Query("SELECT * FROM days ORDER BY dayShort ASC")
//    fun getAllDaysWithTasksAlpha(): Flow<List<DayWithTasks>>
}