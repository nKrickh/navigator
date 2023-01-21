package com.example.krickhand.navigator.dto

data class DayTaskListItem(
    val tId: Long,
    val taskName: String = "",
    val isComplete: Boolean = false
)