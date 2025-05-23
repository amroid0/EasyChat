package com.amroid.chat.data.mappers

import android.icu.text.SimpleDateFormat
import com.amroid.chat.domain.entities.MessageEntity
import com.amroid.data.database.entities.MessageDbEntity
import java.util.Locale

class LocalMessageDataMapper : UserScopedMapper<MessageEntity, MessageDbEntity> {
    override fun map(userId: String, input: MessageEntity): MessageDbEntity {
        return MessageDbEntity(
            id = input.id,
            conversationId = input.conversationId,
            senderId = userId,
            senderName = input.senderName,
            senderAvatar = input.senderAvatar,
            content = input.message,
            timeStamp = convertToMilliSecond(input.timestamp)
        )
    }

    fun convertToMilliSecond(dateString: String): Long {
        try {
            val simpleDateFormat = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
            val date = simpleDateFormat.parse(dateString)
            return date.time
        } catch (ex: Exception) {
        }
        return 0L
    }
}