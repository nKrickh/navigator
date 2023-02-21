package com.example.krickhand.navigator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Priority")
data class Priority(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val priority: String = "",
    val colour: String = ""
)

