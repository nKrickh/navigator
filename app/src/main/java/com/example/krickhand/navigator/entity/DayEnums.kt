package com.example.krickhand.navigator.entity

enum class StatusType(val hexColor: String) {
    OPEN("#14C40080"),
    COMPLETE("#2A1E9BB5"),
    DEFFERED("#B0B0B0"),
    CANCELLED("#000"),
    POSTPONED("#BB2ED0C4")
}

enum class PriorityType(val hexColor: String) {
    LOW("#14c400"),
    MODERATE("#ccf002"),
    MEDIUM("#cf9608"),
    HIGH("#cf4919"),
    URGENT("#cf0808")
}