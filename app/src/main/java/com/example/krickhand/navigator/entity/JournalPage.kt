package com.example.krickhand.navigator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pages")
data class JournalPage(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    // TODO: Flesh it out, bud!
    val content: String = ""
)

