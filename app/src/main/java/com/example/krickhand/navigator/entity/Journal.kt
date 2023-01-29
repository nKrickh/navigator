package com.example.krickhand.navigator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Journals")
data class Journal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    // TODO: Flesh it out, bud!
    val volume_name: String = "",
    val year: Int = 0,
    val collection: String = ""
    // TODO: Type? Topic?
)
