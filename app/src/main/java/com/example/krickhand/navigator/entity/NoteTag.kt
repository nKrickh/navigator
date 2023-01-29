package com.example.krickhand.navigator.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "NoteTags",
    primaryKeys = ["noteId","tagId"],
    foreignKeys = [
        ForeignKey(
            entity = Note::class,
            parentColumns = ["id"],
            childColumns = ["noteId"]
        ),
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["id"],
            childColumns = ["tagId"]
        )
    ],
    indices = [
        (Index(
            name = "index_ntg",
            value = ["tagId"])
                )
    ]
)
data class NoteTag(
    val noteId: Long,
    val tagId: Long
)
