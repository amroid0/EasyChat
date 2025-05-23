package com.amroid.chat.domain.usecases

import com.amroid.chat.domain.entities.ChatRoom
import com.amroid.chat.domain.repos.ChatRoomRepository
import javax.inject.Inject

class GetChatRoomUseCase @Inject constructor(private val repository: ChatRoomRepository) {
    suspend operator fun invoke(chatId: String): ChatRoom {
       return repository.getChatRoomInfo(chatId)
    }
}