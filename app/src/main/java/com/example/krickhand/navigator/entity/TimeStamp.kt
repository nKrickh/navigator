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
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(index = true) var dId: Long = 0,
    @ColumnInfo(index = true) var tId: Long = 0,
    var open: String = "",
    var close: String = "",
    var lastEdit: String? = ""
)

