package com.amroid.chat.data.mappers

import com.amroid.chat.data.mappers.WebSocketDomainMapper.Companion.IMAGE_TYPE
import com.amroid.chat.data.mappers.WebSocketDomainMapper.Companion.TEXT_TYPE
import com.amroid.chat.data.model.WebsocketMessageModel
import com.amroid.chat.domain.entities.MessageEntity
import javax.inject.Inject

class WebSocketMessageDataMapper @Inject constructor() :
    UserScopedMapper<MessageEntity, WebsocketMessageModel> {
    override fun map(userId: String, input: MessageEntity): WebsocketMessageModel {
        return with(input) {
            WebsocketMessageModel(
                id = id,
                message = this.message,
                senderName = senderName,
                senderAvatar = senderAvatar,
                timestamp = timestamp,
                isMine = isMine,
                messageType = if (messageType == com.amroid.chat.domain.entities.MessageEntity.MessageType.TEXT) TEXT_TYPE else IMAGE_TYPE,
                messageDescription = messageDescription,
                conversationId = conversationId
            )
        }

    }}



