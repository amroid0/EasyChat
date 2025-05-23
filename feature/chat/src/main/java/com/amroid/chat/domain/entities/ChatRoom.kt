package com.amroid.chat.domain.entities

data class ChatRoom(
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val lastMessageEntities: List<MessageEntity>
)