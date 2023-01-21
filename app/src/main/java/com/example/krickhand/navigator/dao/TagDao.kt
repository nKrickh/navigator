package com.example.krickhand.navigator.dao

import androidx.room.*
import com.example.krickhand.navigator.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Query("SELECT * FROM tags ORDER BY name ASC")
    fun getAlphabetizedTags(): Flow<List<Tag>>

    // TO-DO: Why won't suspend with in repo?
    @Query("SELECT * FROM days WHERE id = :id")
    fun getTag(id: Long): Flow<Day>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTag(tag: Tag)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTags(tags: List<Tag>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTagTopics(tagTopics: List<TopicTag>)

    @Query("DELETE FROM tags")
    suspend fun deleteAll()

//    // suspend, because no observable flow needed...
//    @Transaction
//    @Query("SELECT * FROM tags WHERE id = :id")
//    suspend fun getTagWithTopics(id: Long): List<TagTopic>
//
//    // TOPICS are only ever associated with Tags... so we don't need another DAO
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTopic(topic: Topic)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTopics(topics: List<Topic>)


}