package com.amroid.chat.ui.model

data class Message(
    val id: String,
    val avatar: String,
    val messageContent: MessageContent,
    val userName: String,
    val messageDate: String,
    val isMine: Boolean
)

sealed class MessageContent {
    data class TextConTent(val text: String) : MessageContent()
    data class ImageContent(val url: String, val contentDescription: String) : MessageContent()
}
