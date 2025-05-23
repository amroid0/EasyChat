package com.amroid.chat.domain.repos

import com.amroid.chat.domain.entities.ChatRoom

interface ChatRoomRepository {
    suspend fun getChatRoomInfo(id:String): ChatRoom
}