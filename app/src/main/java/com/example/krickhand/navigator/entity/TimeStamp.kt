package com.example.krickhand.navigator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Timestamps")
data class TimeStamp(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val stamp: String = "",
    val lastEdit: String? = ""
)
