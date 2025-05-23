package com.amroid.chat.domain.usecases

import com.amroid.chat.domain.entities.MessageEntity
import com.amroid.chat.domain.repos.MessageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val repository: MessageRepository) {
    suspend operator fun invoke(chatId: String, userId: String): Flow<MessageEntity> {
        return repository.getMessages(chatId, userId)
    }
}