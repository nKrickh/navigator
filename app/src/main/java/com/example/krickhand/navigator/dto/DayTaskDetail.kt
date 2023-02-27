package com.example.krickhand.navigator.dto

import com.example.krickhand.navigator.entity.TaskTag
import com.example.krickhand.navigator.entity.TimeStamp

class DayTaskDetail(
    val dId: Long = 0,
    val tId: Long = 0,
    val dayShort: String = "",
    val taskName: String = "",
    val taskDesc: String = "",
    val isComplete: Boolean = false,
    val scheduledStart: String? = "",
    val scheduledEnd: String? = "",
    val desc: String? = "",
    val status: String? = "",
    val priority: String? = ""
)
