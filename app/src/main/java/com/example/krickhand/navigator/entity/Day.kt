package com.example.krickhand.navigator.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_table")
data class Day(
    @PrimaryKey(autoGenerate = true) val dayId: Long = 0,
    @ColumnInfo val dayShort: String,
    @ColumnInfo val dayLong: String
)