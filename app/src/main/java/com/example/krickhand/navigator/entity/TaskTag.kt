package com.example.krickhand.navigator.entity

import androidx.room.*

@Entity(
    tableName = "TaskTags",
    primaryKeys = ["taskId","tagId"],
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["taskId"]
        ),
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["id"],
            childColumns = ["tagId"]
        )
    ],
    indices = [
        (Index(
            name = "index_tsktg",
            value = ["tagId"])
        )
    ]
)
data class TaskTag (
    val taskId: Long,
    val tagId: Long
)
