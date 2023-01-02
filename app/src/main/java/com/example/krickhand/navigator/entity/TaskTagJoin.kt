package com.example.krickhand.navigator.entity

import androidx.room.*

@Entity(
    tableName = "TaskTag_Join",
    primaryKeys = ["taskId","tagId"]
)
data class TaskTagJoin (
    @ColumnInfo val taskId: Long,
    @ColumnInfo val tagId: Long
)

data class TaskWithTags(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "tagId",
        associateBy = Junction(TaskTagJoin::class)
    )
    val tags: List<Tag>
)

data class TagWithTasks(
    @Embedded val tag: Tag,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "taskId",
        associateBy = Junction(TopicTagJoin::class)
    )
    val tasks: List<Task>
)
