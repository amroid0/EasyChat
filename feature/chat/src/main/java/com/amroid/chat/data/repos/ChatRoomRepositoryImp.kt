package com.amroid.chat.data.repos

import com.amroid.chat.data.datasources.ChatRoomDataSource
import com.amroid.chat.domain.entities.ChatRoom
import com.amroid.chat.domain.repos.ChatRoomRepository
import javax.inject.Inject

class ChatRoomRepositoryImp @Inject constructor(private val dataSource: ChatRoomDataSource) :
    ChatRoomRepository {
    override suspend fun getChatRoomInfo(id: String): ChatRoom {
        return dataSource.getChatRoomInfo(id).toDomain()
    }
}