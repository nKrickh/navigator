package com.example.krickhand.navigator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Status")
data class Status(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val status: String = ""
)