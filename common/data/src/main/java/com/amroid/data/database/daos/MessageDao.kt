package com.amroid.data.database.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amroid.data.database.entities.MessageDbEntity
import kotlinx.coroutines.flow.Flow

interface MessageDao{
   @Query("select * from messages where conversation_id =:conversationId order by timestamp ASC ")
  fun getAllMessage(conversationId:String):Flow<List<MessageDbEntity>>
 @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMessage(messageDbEntity: MessageDbEntity)
 @Delete
  suspend fun deleteMessage(messageDbEntity: MessageDbEntity)



}