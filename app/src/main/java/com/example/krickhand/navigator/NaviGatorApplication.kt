package com.example.krickhand.navigator

import android.app.Application
import com.example.krickhand.navigator.repo.DayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NaviGatorApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { NavigatorRoomDB.getDatabase(this, applicationScope) }
    val repository by lazy { DayRepository(database.dayDao()) }
}