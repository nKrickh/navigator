package com.example.krickhand.navigator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.krickhand.navigator.dao.DayDao
import com.example.krickhand.navigator.dao.TagDao
import com.example.krickhand.navigator.dao.TaskDao
import com.example.krickhand.navigator.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

// Annotates class to be a Room Database with a table (entity) of the Day class
@Database(
    entities = [
        Day::class,
        Task::class,
        Tag::class,
        Topic::class,
        DayTaskJoin::class,
        TaskTagJoin::class,
        TopicTagJoin::class
       ],
    version = 1,
    exportSchema = false)
public abstract class NavigatorRoomDB: RoomDatabase() {

    abstract fun dayDao(): DayDao
    abstract fun taskDao(): TaskDao
    abstract fun tagDao(): TagDao

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
                    populateDatabase(
                        database.dayDao(),
                        database.taskDao(),
                        database.tagDao()
                    )
                }
            }
        }

        suspend fun populateDatabase(
            dayDao: DayDao,
            taskDao: TaskDao,
            tagDao: TagDao
        ) {
            // A fresh start
            dayDao.deleteAll()
            taskDao.deleteAll()
            tagDao.deleteAll()

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


            //val occasions= getOccasionMap(currentYear)
            // populate sample tasks
            var taskId = 1L
            val tasks = mutableListOf<Task>()
            val daytasks = mutableListOf<DayTaskJoin>()
            val sampleTasks = arrayOf("Fry the fish", "Think more about geology", "Dance briskly")
            for (task in sampleTasks) {
                tasks.add(Task(taskId, task, "Level $taskId"))
                daytasks.add(DayTaskJoin(1, taskId++))
            }

            // TODO: test purpose at this point
//            for (sample in tasks) {
//                daytasks.add(DayTaskJoin(1, sample.taskId))
//            }

            var tagId = 1L
            var topicId = 1L
            var topicCounter = 1L
            val tags = mutableListOf<Tag>()
            val topics = mutableListOf<Topic>()
            val tagWithTopics = mutableListOf<TopicTagJoin>()
            val sampleTags = arrayOf("Tag 1", "Tag 2", "Tag 3", "Tag 4", "Tag 5", "Tag 6")
            val sampleTopics = arrayOf("Topical Inquiries", "Raging Sadism", "Tendrils of Tinnitus")

            for (topic in sampleTopics) topics.add(Topic(topicId++, topic))
            for (tag in sampleTags) {
                tags.add(Tag(tagId, tag, "Tag of the $tagId"))
                tagWithTopics.add(TopicTagJoin(topicCounter++, tagId++))
                if (topicCounter > 3)
                    topicCounter = 1L
            }

            val sampleTaskTags = mutableListOf<TaskTagJoin>(
                TaskTagJoin(1, 2),
                TaskTagJoin(1, 5),
                TaskTagJoin(2, 4),
                TaskTagJoin(3, 6),
                TaskTagJoin(3, 2)
            )

            dayDao.insertDays(yearOfDays)
            taskDao.insertTasks(tasks)
            tagDao.insertTags(tags)
            tagDao.insertTopics(topics)
            tagDao.insertTagTopics(tagWithTopics)
            taskDao.insertTaskTags(sampleTaskTags)
            dayDao.insertDayTasks(daytasks)

        }
    }
}