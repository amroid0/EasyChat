package com.amroid.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
data class ConversationDbEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "last_timestamp")
    val lastMessageTimeStamp: Long
)