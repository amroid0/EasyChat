package com.amroid.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amroid.data.database.entities.ConversationDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {
    @Query("Select * from conversations order by last_timestamp DESC")
    fun getConversationList(conversationId: Int): Flow<List<ConversationDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversation(conversationDbEntity: ConversationDbEntity)

    @Delete
    suspend fun deleteConversation(conversationDbEntity: ConversationDbEntity)
}

