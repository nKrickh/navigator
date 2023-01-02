package com.example.krickhand.navigator.entity

import androidx.room.*


@Entity(
    tableName = "TopicTag_Join",
    primaryKeys = ["topicId", "tagId"]
)
data class TopicTagJoin (
    @ColumnInfo val topicId: Long,
    @ColumnInfo val tagId: Long
)

data class TopicWithTags(
    @Embedded val topic: Topic,
    @Relation(
        parentColumn = "topicId",
        entityColumn = "tagId",
        associateBy = Junction(TopicTagJoin::class)
    )
    val tags: List<Tag>
)

data class TagWithTopics(
    @Embedded val tag: Tag,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "topicId",
        associateBy = Junction(TopicTagJoin::class)
    )
    val topics: List<Topic>
)