package com.example.krickhand.navigator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.krickhand.navigator.dao.DayDao
import com.example.krickhand.navigator.entity.Day
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date

// Annotates class to be a Room Database with a table (entity) of the Day class
@Database(
    entities = [
        Day::class
               ],
    version = 1,
    exportSchema = false)
public abstract class NavigatorRoomDB: RoomDatabase() {

    abstract fun dayDao(): DayDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NavigatorRoomDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NavigatorRoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NavigatorRoomDB::class.java,
                    "navi_db"
                )
                    .addCallback(NavigatorDBCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class NavigatorDBCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.dayDao())
                }
            }
        }

        suspend fun populateDatabase(dayDao: DayDao) {
            // A fresh start
            dayDao.deleteAll()

            // Build a year
            val currentYear = LocalDate.now().year
            var day = LocalDate.of(currentYear, 1, 1)
            val totalDaysInYear: Int = if(day.isLeapYear) 366 else 365

            var dayId = 1L
            val yearOfDays = mutableListOf(
                Day(dayId++, day.toString(), day.format(
                    DateTimeFormatter.ofLocalizedDate(
                        FormatStyle.LONG)))
            )

            var count = 1
            while (count++ < totalDaysInYear) {
                day = day.plusDays(1)
                yearOfDays.add(Day(dayId++, day.toString(), day.format(
                    DateTimeFormatter.ofLocalizedDate(
                        FormatStyle.LONG))))
            }

            dayDao.insertDays(yearOfDays)
            //val occasions= getOccasionMap(currentYear)

        }
    }
}