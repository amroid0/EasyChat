package com.amroid.chat.data.model
import com.amroid.chat.domain.entities.ChatRoom
import kotlinx.serialization.Serializable

@Serializable
data class ChatRoomInfo(
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val lastMessages: List<WebsocketMessageModel>
) {
    fun toDomain(): ChatRoom {
        return ChatRoom(id = id,
            senderName = senderName,
            senderAvatar = senderAvatar,
            lastMessageEntities = lastMessages.map { it.toDomain() })
    }
}