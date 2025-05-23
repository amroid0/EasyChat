package com.amroid.chat.data.datasources

import com.amroid.data.database.daos.MessageDao
import com.amroid.data.database.entities.ConversationDbEntity
import com.amroid.data.database.entities.MessageDbEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMessagingDataSource @Inject constructor(private val messageDao: MessageDao) {

    fun getMessagesByConversationId(conversationId: String): Flow<List<MessageDbEntity>> =
        messageDao.getAllMessage(conversationId)

    suspend fun insertMessage(messageDbEntity: MessageDbEntity) =
        messageDao.insertMessage(messageDbEntity)

    suspend fun deleteMessage(messageDbEntity: MessageDbEntity) =
        messageDao.deleteMessage(messageDbEntity)

}