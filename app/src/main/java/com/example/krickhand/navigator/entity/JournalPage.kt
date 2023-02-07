package com.example.krickhand.navigator.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

private const val CHAR_COUNT = 1800
@Entity(tableName = "Pages",
    foreignKeys = [
        ForeignKey(
            entity = Journal::class,
            parentColumns = ["id"],
            childColumns = ["journalId"]
        ),
        ForeignKey(
            entity = Day::class,
            parentColumns = ["id"],
            childColumns = ["dayId"]
        )],
    indices = [
        (Index(
            name = "index_jp",
            value = ["dayId", "journalId"])
        )
    ]
)
data class JournalPage(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    // TODO: Flesh it out, bud!
    // IDEA: this is an 1800 char string... make this a CONST sorta variable?
    val content: String = "",
    val journalId: Long,
    val dayId: Long
)

