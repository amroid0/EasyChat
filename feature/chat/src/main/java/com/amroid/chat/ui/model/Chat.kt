package com.amroid.chat.ui.model

import com.amroid.chat.domain.entities.ChatRoom


data class Chat(
    val id: String? = null,
    val name: String? = null,
    val avatar: String? = null
)

fun ChatRoom.toUI() = run {
    Chat(
        id = id,
        name = senderName,
        avatar = senderAvatar
    )
}