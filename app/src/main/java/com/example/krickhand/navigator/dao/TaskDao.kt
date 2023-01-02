package com.example.krickhand.navigator.dao

import androidx.room.*
import com.example.krickhand.navigator.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY name ASC")
    fun getAlphabetizedTasks(): Flow<List<Task>>

    // TO-DO: Why won't suspend with in repo?
    @Query("SELECT * FROM tasks WHERE taskId = :id")
    fun getTask(id: Long): Flow<Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTasks(tasks: List<Task>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTaskTags(tags: List<TaskTagJoin>)

    @Query("DELETE FROM tasks")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :id")
    fun getTaskWithTags(id: Long): Flow<TaskWithTags>
}