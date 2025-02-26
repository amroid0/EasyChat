package com.amroid.home.ui.model

data class Conversation(
     val id: String,
     val avatarUrl: String,
     val userName: String,
     val lastMessage: String,
     val lastMessageDate: String,
     val messageCount: Int = 0
)
