package com.example.krickhand.navigator.entity

import androidx.room.*

@Entity(
    tableName = "DayTask_Join",
    primaryKeys = ["dayId", "taskId"]
)
data class DayTaskJoin(
    @ColumnInfo val dayId: Long,
    @ColumnInfo val taskId: Long
)

data class DayWithTasks(
    @Embedded val day: Day,
    @Relation(
        parentColumn = "dayId",
        entityColumn = "taskId",
        associateBy = Junction(DayTaskJoin::class)
    )
    val tasks: List<Task>
)

data class TaskWithDays(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "dayId",
        associateBy = Junction(DayTaskJoin::class)
    )
    val days: List<Day>
)

