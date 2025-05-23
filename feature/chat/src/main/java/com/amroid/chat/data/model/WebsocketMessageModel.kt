package com.amroid.chat.data.model

import com.amroid.chat.domain.entities.MessageEntity
import kotlinx.serialization.Serializable

@Serializable
class WebsocketMessageModel(
    val id: String,
    val message: String,
    val senderName: String,
    val senderAvatar: String,
    val timestamp: String,
    val isMine: Boolean,
    val messageType: String,
    val messageDescription: String,
      val conversationId: String,

    )