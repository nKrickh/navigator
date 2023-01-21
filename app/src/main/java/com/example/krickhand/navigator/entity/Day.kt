package com.example.krickhand.navigator.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Days")
data class Day(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo val dayShort: String,
    @ColumnInfo val dayLong: String
)