package com.example.krickhand.navigator.entity

import androidx.room.*


@Entity(
    tableName = "TopicTags",
    primaryKeys = ["topicId", "tagId"],
    foreignKeys = [
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["id"],
            childColumns = ["tagId"]
        ),
        ForeignKey(
            entity = Topic::class,
            parentColumns = ["id"],
            childColumns = ["topicId"]
        )
    ],
    indices = [
        (Index(
            name = "index_tptg",
            value = ["tagId"])
        )
    ]
)
data class TopicTag (
    val topicId: Long,
    val tagId: Long
)

//data class TopicWithTags(
//    @Embedded val topic: Topic,
//    @Relation(
//        parentColumn = "topicId",
//        entityColumn = "tagId",
//        associateBy = Junction(TopicTag::class)
//    )
//    val tags: List<Tag>
//)
//
//data class TagWithTopics(
//    @Embedded val tag: Tag,
//    @Relation(
//        parentColumn = "tagId",
//        entityColumn = "topicId",
//        associateBy = Junction(TopicTag::class)
//    )
//    val topics: List<Topic>
//)