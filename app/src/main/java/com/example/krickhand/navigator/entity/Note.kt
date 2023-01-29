package com.example.krickhand.navigator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    // TODO: Flesh it out, bud!
    val content: String = ""
)
