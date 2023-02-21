package com.example.krickhand.navigator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.krickhand.navigator.dao.DayDao
import com.example.krickhand.navigator.dao.JournalDao
import com.example.krickhand.navigator.dao.NoteDao
import com.example.krickhand.navigator.dao.TagDao
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
        Tag::class,
        Topic::class,
        Task::class,
        Status::class,
        Priority::class,
        TimeStamp::class,
        Note::class,

        DayTask::class,
        TaskTag::class,
        TopicTag::class,
        NoteTag::class,

        Journal::class,
        JournalPage::class,
    ],
    version = 1,
    exportSchema = false)
abstract class NavigatorRoomDB: RoomDatabase() {

    abstract fun dayDao(): DayDao
    abstract fun noteDao(): NoteDao
    abstract fun journalDao(): JournalDao
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
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(
                        database.dayDao(),
                        database.tagDao()
                    )
                }
            }
        }

        suspend fun populateDatabase(
            dayDao: DayDao,
            tagDao: TagDao
        ) {
            // A fresh start
            dayDao.deleteAllDays()
            dayDao.deleteAllTasks()
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
            // populate sample daytask data
            var statusId = 1L
            val statusTypes = StatusType.values()
            val statuses = mutableListOf<Status>()
            for (status in statusTypes) {
                statuses.add(Status(statusId++, status.toString(), status.hexColor))
            }

            var priorityId = 1L
            val priorityTypes = PriorityType.values()
            val priorities = mutableListOf<Priority>()
            for (priority in priorityTypes) {
                priorities.add(Priority(priorityId++, priority.toString(), priority.hexColor))
            }

            var taskId = 1L
            val tasks = mutableListOf<Task>()
            val daytasks = mutableListOf<DayTask>()
            val sampleTasks = arrayOf("Fry the fish", "Think more about geology", "Dance briskly")
            for (task in sampleTasks) {
                tasks.add(Task(taskId, task, "This task worth ${taskId - 2 * 8} POINTS!"))
                daytasks.add(DayTask
                    (1, taskId, 1, 1,
                    scheduledStart = LocalDate.now().toString(),
                    scheduledEnd = LocalDate.now().plusDays(2).toString(),
                    desc = "Pickled folly, the lot of it! Why $task at all?"
                    )
                )
                taskId++
            }

            var tagId = 1L
            var topicId = 1L
            var topicCounter = 1L

            val tags = mutableListOf<Tag>()
            val topics = mutableListOf<Topic>()
            val tagWithTopics = mutableListOf<TopicTag>()
            val sampleTags = arrayOf("Practical", "Health", "Code", "Kierkegaard", "Dialectical Materialism", "Fetish")
            val sampleTopics = arrayOf("Topical Inquiries", "Raging Sadism", "Tendrils of Tinnitus")

            var colorCounter = 0
            val colorArray = arrayOf("#129635", "#3933d6", "#87376d", "#bd1900", "#307a5e", "#a0db95")

            for (topic in sampleTopics) {
                topics.add(Topic(topicId++, topic, "$topic requires clarity", colorArray[colorCounter]))
                if (++colorCounter > 5) colorCounter = 0
            }

            for (tag in sampleTags) {
                tags.add(Tag(tagId, tag, "Let's see about that! Worth ${tagId - 5} POINTS!"))
                tagWithTopics.add(TopicTag(topicCounter++, tagId++))
                if (topicCounter > 3) topicCounter = 1L
            }

            val sampleTaskTags = mutableListOf(
                TaskTag(1, 2),
                TaskTag(1, 5),
                TaskTag(2, 4),
                TaskTag(3, 6),
                TaskTag(3, 2)
            )

            dayDao.insertDays(yearOfDays)
            dayDao.insertTasks(tasks)
            tagDao.insertTags(tags)
            tagDao.insertTopics(topics)
            tagDao.insertTagTopics(tagWithTopics)
            dayDao.insertTaskTags(sampleTaskTags)
            dayDao.insertStatuses(statuses)
            dayDao.insertPriorities(priorities)
            dayDao.insertDayTasks(daytasks)

        }
    }
}