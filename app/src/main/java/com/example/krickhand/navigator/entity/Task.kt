package com.example.krickhand.navigator.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val taskId: Long = 0,
    @ColumnInfo val name: String,
    @ColumnInfo val desc: String,
)
