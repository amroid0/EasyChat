package com.amroid.chat.data.mappers

import com.amroid.chat.data.model.FirestoreMessageModel
import com.amroid.chat.domain.entities.MessageEntity
import com.google.firebase.Timestamp
import javax.inject.Inject


class MessageDataMapper @Inject constructor() :
    UserScopedMapper<MessageEntity, FirestoreMessageModel> {
    override fun map(userId: String, input: MessageEntity): FirestoreMessageModel {
        return FirestoreMessageModel(
            id = input.id,
            senderAvatar = input.senderAvatar,
            senderName = input.senderName,
            content = input.message,
            timeStamp = Timestamp.now(),
            conversationId = input.conversationId
        )
    }

}



