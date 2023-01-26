package com.example.krickhand.navigator.dao

import androidx.room.*
import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.dto.DayTaskListItem
import com.example.krickhand.navigator.entity.Day
import com.example.krickhand.navigator.entity.DayTask
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDao {
    @Query("SELECT * FROM days ORDER BY dayShort ASC")
    fun getAlphabetizedDays(): Flow<List<Day>>

    // TO-DO: Why won't suspend with in repo? BECAUSE IT'S FLOW/OBSERVABLE!
    @Query("SELECT * FROM days WHERE id = :id")
    fun getDay(id: Long): Flow<Day>

    @Query("SELECT * FROM daytasks WHERE dayId = :id")
    fun getDayTasks(id: Long): Flow<List<DayTask>>

    @Query(
        "SELECT d.dayShort, d.id AS dId, t.name AS taskName, t.id AS tId, t.`desc` AS taskDesc, dt.isComplete, dt.scheduledStart, dt.scheduledEnd, dt.`desc` FROM days AS d " +
                "JOIN daytasks AS dt ON dt.dayId = d.id " +
                "JOIN tasks AS t ON dt.taskId = t.id " +
                "WHERE t.id = :id"
    )
    fun loadDayTaskDetails(id: Long): DayTaskDetail

    @Query("SELECT t.id AS tId, t.name AS taskName, dt.isComplete FROM tasks AS t " +
            "JOIN daytasks AS dt ON dt.taskId = t.id " +
            "JOIN days AS d ON dt.dayId = d.id " +
            "WHERE d.id = :id")
    fun loadDayTaskList(id: Long): Flow<List<DayTaskListItem>>


    // coroutines! not on main UI thread
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDay(day: Day)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDays(days: List<Day>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayTasks(days: List<DayTask>)

    @Query("DELETE FROM days")
    suspend fun deleteAll()

}