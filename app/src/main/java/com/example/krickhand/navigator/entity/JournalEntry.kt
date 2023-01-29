package com.example.krickhand.navigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "JournalEntries",
    primaryKeys = ["journalId", "pageId"],
    foreignKeys = [
        ForeignKey(
            entity = Journal::class,
            parentColumns = ["id"],
            childColumns = ["journalId"]
        ),
        ForeignKey(
            entity = JournalPage::class,
            parentColumns = ["id"],
            childColumns = ["pageId"]
        )],
    indices = [
        (Index(
            name = "index_jp",
            value = ["pageId"])
        )
    ]
)
data class JournalEntry(
    val journalId: Long,
    val pageId: Long,
    val date: String
)
