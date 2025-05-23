package com.amroid.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "messages", foreignKeys = [
    ForeignKey(
        entity = ConversationDbEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("conversation_id"),
        onDelete = ForeignKey.CASCADE,
    )
])
data class MessageDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "conversation_id")
    val  conversationId:String,
    @ColumnInfo(name = "sender_id")
    var senderId: String,
    @ColumnInfo(name = "sender_name")
    var senderName: String,
    @ColumnInfo(name = "sender_avatar")
    var senderAvatar: String,
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "timestamp")
    var timeStamp: Long
)
