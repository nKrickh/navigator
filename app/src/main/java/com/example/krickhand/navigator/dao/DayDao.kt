package com.example.krickhand.navigator.dao

import androidx.room.*
import com.example.krickhand.navigator.dto.DayTaskDetail
import com.example.krickhand.navigator.entity.*
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

//    @Query(
//        "SELECT d.dayShort, d.id AS dId, t.name AS taskName, t.id AS tId, t.`desc` AS taskDesc, dt.isComplete, dt.scheduledStart, dt.scheduledEnd, dt.`desc` FROM days AS d " +
//            "JOIN daytasks AS dt ON dt.dayId = d.id " +
//            "JOIN tasks AS t ON dt.taskId = t.id " +
//            "WHERE t.id = :id"
//    )
//    suspend fun loadDayTaskDetails(id: Long): DayTaskDetail

    @Query(
        "SELECT d.dayShort, d.id AS dId, t.id AS tId, t.name AS taskName, t.`desc` AS taskDesc, dt.isComplete, dt.scheduledStart, dt.scheduledEnd, dt.`desc`, s.status, p.priority FROM daytasks AS dt " +
           "JOIN status AS s on dt.statusId = s.id " +
           "JOIN priority AS p on dt.priorityId = p.id " +
           "JOIN tasks AS t ON dt.taskId = t.id " +
           "JOIN days AS d ON dt.dayId = d.id " +
            "WHERE d.id = :id"
    )
    fun loadDayTaskList(id: Long): Flow<List<DayTaskDetail>>

    @Query(
        "SELECT tags.id, tags.name, tags.`desc` FROM tags JOIN tasktags AS tt on tags.id = tt.tagId WHERE tt.taskId = :id"
    )
    fun loadTaskTags(id: Long): Flow<List<Tag>>

    // coroutines! not on main UI thread
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDay(day: Day)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDays(days: List<Day>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayTasks(days: List<DayTask>)

    @Query("DELETE FROM days")
    suspend fun deleteAllDays()

    /**
     * TASKS
     * */
    @Query("SELECT * FROM tasks ORDER BY name ASC")
    fun getAlphabetizedTasks(): Flow<List<Task>>

    // TO-DO: Why won't suspend with in repo?
    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTask(id: Long): Flow<Task>

    @Query("SELECT * FROM status")
    suspend fun getStatuses(): List<Status>

    @Query("SELECT * FROM priority")
    suspend fun getPriorities(): List<Priority>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTasks(tasks: List<Task>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTaskTags(tags: List<TaskTag>)

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStatuses(statuses: List<Status>)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPriorities(priorities: List<Priority>)

}