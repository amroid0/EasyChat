package com.amroid.chat.data.mappers

import com.amroid.chat.domain.entities.MessageEntity
import com.amroid.data.database.entities.MessageDbEntity
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

class LocalMessageDomainMapper : UserScopedMapper<MessageDbEntity, MessageEntity> {
    override fun map(userId: String, input: MessageDbEntity): MessageEntity {

        return MessageEntity(
            id = input.id,
            message = input.content,
            senderName = input.senderName,
            senderAvatar = input.senderAvatar,
            timestamp = formatDate(input.timeStamp),
            isMine = userId == input.senderId,
            messageType = MessageEntity.MessageType.TEXT,
            messageDescription = "",
            conversationId = input.conversationId
        )
    }

    fun formatDate(timeStamp: Long): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
        return simpleDateFormat.format(timeStamp)
    }
}