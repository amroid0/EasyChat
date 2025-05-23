package com.amroid.chat.data.repos

import com.amroid.chat.data.datasources.FirebaseMessagingDataSource
import com.amroid.chat.data.mappers.UserScopedMapper
import com.amroid.chat.data.model.FirestoreMessageModel
import com.amroid.chat.domain.entities.MessageEntity
import com.amroid.chat.domain.repos.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseMessageRepository @Inject constructor(
    private val firebaseMessagingDataSource: FirebaseMessagingDataSource,
    val messageDomainMapper: UserScopedMapper<FirestoreMessageModel, MessageEntity>,
    val messageDataMapper: UserScopedMapper<MessageEntity, FirestoreMessageModel>

) :
    MessageRepository {
    override suspend fun getMessages(chatId: String, userId: String): Flow<MessageEntity> {
        return firebaseMessagingDataSource.getMessages(chatId = chatId, userId = userId).map {
            messageDomainMapper.map(userId, it)
        }
    }

    override suspend fun sendMessages(chatId: String, message: MessageEntity) {
        val messageData = messageDataMapper.map("", message)
        firebaseMessagingDataSource.sendMessage(chatId, messageData)
    }


    override suspend fun disconnect() {
    }
}