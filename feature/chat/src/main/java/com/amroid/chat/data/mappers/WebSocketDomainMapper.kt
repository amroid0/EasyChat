package com.amroid.chat.data.mappers

import com.amroid.chat.data.model.WebsocketMessageModel
import com.amroid.chat.domain.entities.MessageEntity
import javax.inject.Inject

class WebSocketDomainMapper @Inject constructor() :
    UserScopedMapper<WebsocketMessageModel, MessageEntity> {
    override fun map(userId: String, input: WebsocketMessageModel): MessageEntity {
        return with(input) {
            MessageEntity(
                id = id,
                message = message,
                senderName = senderName,
                senderAvatar = senderAvatar,
                timestamp = timestamp,
                isMine = isMine,
                messageType = getContentType(messageType),
                messageDescription = messageDescription,
                conversationId = conversationId
            )

        }

    }

    private fun getContentType(messageType: String): MessageEntity.MessageType {
        return when (messageType) {
            IMAGE_TYPE -> MessageEntity.MessageType.IMAGE
            else -> MessageEntity.MessageType.TEXT

        }
    }

    companion object {

        const val TEXT_TYPE = "TEXT"
        const val IMAGE_TYPE = "IMAGE"

    }
}



