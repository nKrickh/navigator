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

//data class TaskWithTags(
//    @Embedded val task: Task,
//    @Relation(
//        parentColumn = "taskId",
//        entityColumn = "tagId",
//        associateBy = Junction(TaskTag::class)
//    )
//    val tags: List<Tag>
//)
//
//data class TagWithTasks(
//    @Embedded val tag: Tag,
//    @Relation(
//        parentColumn = "tagId",
//        entityColumn = "taskId",
//        associateBy = Junction(TopicTag::class)
//    )
//    val tasks: List<Task>
//)
