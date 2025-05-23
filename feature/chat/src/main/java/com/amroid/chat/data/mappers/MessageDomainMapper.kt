package com.amroid.chat.data.mappers

import com.amroid.chat.data.model.FirestoreMessageModel
import com.amroid.chat.domain.entities.MessageEntity
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MessageDomainMapper @Inject constructor() :
    UserScopedMapper<FirestoreMessageModel, MessageEntity> {
    override fun map(userId: String, input: FirestoreMessageModel): MessageEntity {
        return MessageEntity(
            id = input.id,
            senderAvatar = input.senderAvatar,
            senderName = input.senderName,
            message = input.content,
            messageType = MessageEntity.MessageType.TEXT,
            timestamp = formatDate(input.timeStamp),
            messageDescription = "",
            isMine = userId == input.senderId,
            conversationId = input.conversationId
        )
    }

    fun formatDate(timeStamp: Timestamp): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
        return simpleDateFormat.format(timeStamp.toDate())
    }
}



