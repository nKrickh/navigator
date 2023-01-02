package com.example.krickhand.navigator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Topics")
data class Topic(
    @PrimaryKey(autoGenerate = true) val topicId: Long = 0,
    val name: String,
    val desc: String? = "",
    val color: String? = ""
)
