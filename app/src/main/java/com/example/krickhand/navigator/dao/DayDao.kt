package com.example.krickhand.navigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.krickhand.navigator.entity.Day
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDao {
    @Query("SELECT * FROM day_table ORDER BY dayShort ASC")
    fun getAlphabetizedDays(): Flow<List<Day>>

    // TO-DO: Why won't suspend with in repo?
    @Query("SELECT * FROM day_table WHERE dayId = :id")
    fun getDay(id: Long): Flow<Day>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDay(day: Day)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDays(days: List<Day>)

    @Query("DELETE FROM day_table")
    suspend fun deleteAll()
}