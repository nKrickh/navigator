package com.example.krickhand.navigator.entity

import androidx.room.*

@Entity(
    tableName = "DayTasks",
    primaryKeys = ["dayId", "taskId"],
    foreignKeys = [
        ForeignKey(
            entity = Day::class,
            parentColumns = ["id"],
            childColumns = ["dayId"]
        ),
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["taskId"]
        ),
        ForeignKey(
            entity = Status::class,
            parentColumns = ["id"],
            childColumns = ["statusId"]
        ),
        ForeignKey(
            entity = Priority::class,
            parentColumns = ["id"],
            childColumns = ["priorityId"]
        )]
)
data class DayTask(
    val dayId: Long,
    @ColumnInfo(index = true)
    val taskId: Long,
    @ColumnInfo(index = true)
    val priorityId: Long,
    @ColumnInfo(index = true)
    val statusId: Long,
    val isComplete: Boolean? = false,
    val scheduledStart: String? = "",
    val scheduledEnd: String? = "",
    val desc: String? = ""
)

//data class DayWithTasks(
//    @Embedded val day: Day,
//    @Relation(
//        parentColumn = "dayId",
//        entityColumn = "taskId",
//        associateBy = Junction(DayTask::class)
//    )
//    val tasks: List<Task>
//)
//
//data class TaskWithDays(
//    @Embedded val task: Task,
//    @Relation(
//        parentColumn = "taskId",
//        entityColumn = "dayId",
//        associateBy = Junction(DayTask::class)
//    )
//    val days: List<Day>
//)

