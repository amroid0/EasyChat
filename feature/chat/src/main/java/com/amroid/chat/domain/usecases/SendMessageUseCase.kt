package com.amroid.chat.domain.usecases

import com.amroid.chat.domain.entities.MessageEntity
import com.amroid.chat.domain.repos.MessageRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val repository: MessageRepository) {
    suspend operator fun invoke(chatId:String ,message: MessageEntity) {
     repository.sendMessages(chatId,message)
    }
}