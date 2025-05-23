package com.amroid.chat.domain.repos

import com.amroid.chat.domain.entities.MessageEntity
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    suspend fun getMessages(chatId: String, userId: String): Flow<MessageEntity>
    suspend fun sendMessages(chatId: String,message:MessageEntity)
    suspend fun disconnect()

}