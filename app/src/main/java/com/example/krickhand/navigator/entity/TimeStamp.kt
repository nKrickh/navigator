package com.example.krickhand.navigator.entity

import androidx.room.*

@Entity(
    tableName = "Timestamps",
    foreignKeys = [
        ForeignKey(
        entity = DayTask::class,
        parentColumns = ["dayId", "taskId"],
        childColumns = ["dId", "tId"]
    )],
    indices = [
        Index(value = ["dId", "tId"])
    ]
)
data class TimeStamp(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(index = true) val dId: Long,
    @ColumnInfo(index = true) val tId: Long,
    val open: String = "",
    val close: String = "",
    val lastEdit: String? = ""
)
