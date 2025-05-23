package com.amroid.chat.domain.entities

data class MessageEntity(
    val id: String,
    val message: String,
    val senderName: String,
    val senderAvatar: String,
    val timestamp: String,
    val isMine: Boolean,
    val messageType: MessageType,
    val messageDescription: String,
    val conversationId: String,

    ) {


    enum class MessageType {
        TEXT, IMAGE
    }
}
