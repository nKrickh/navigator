package com.example.krickhand.navigator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.krickhand.navigator.entity.Day
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

    @Query("DELETE FROM days")
    suspend fun deleteAll()
}