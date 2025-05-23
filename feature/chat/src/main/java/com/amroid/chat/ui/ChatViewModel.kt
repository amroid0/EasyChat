package com.amroid.chat.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amroid.chat.domain.entities.ChatRoom
import com.amroid.chat.domain.entities.MessageEntity
import com.amroid.chat.domain.usecases.DisconnectUseCase
import com.amroid.chat.domain.usecases.GetChatRoomUseCase
import com.amroid.chat.domain.usecases.GetMessageUseCase
import com.amroid.chat.domain.usecases.SendMessageUseCase
import com.amroid.chat.ui.model.Chat
import com.amroid.chat.ui.model.Message
import com.amroid.chat.ui.model.MessageContent
import com.amroid.chat.ui.model.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getMessageUseCase: GetMessageUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val disconnectUseCase: DisconnectUseCase,
    private val getChatRoomUseCase: GetChatRoomUseCase
) : ViewModel() {
    private lateinit var chatJob: Job
    private var chatRoom: ChatRoom? = null
    private val _chatState = MutableStateFlow(Chat())
    val chatInfoState: StateFlow<Chat> = _chatState
    private val _messageState = MutableStateFlow<List<Message>>(emptyList())
    val messageList: StateFlow<List<Message>> = _messageState


    fun retrieveMessages() {
        chatJob = viewModelScope.launch {
            getMessageUseCase("1","1").map { it.toMessageUi() }.collect {
                val currentMessages = _messageState.value
                if (it.id !in currentMessages.map { it.id }) {
                    _messageState.value += it
                }
            }
        }
    }

    fun sendMessage(messageString: String) {
        viewModelScope.launch {

            val message = MessageEntity(
                id = "1",
                senderAvatar = "",
                senderName = "Amr",
                isMine = true,
                messageType = MessageEntity.MessageType.TEXT,
                message = messageString,
                timestamp = "",
                messageDescription = messageString,
                conversationId = "1"
            )
            sendMessageUseCase("1",message)
        }
    }

    fun loadChatInfo() {
        viewModelScope.launch {
            try {
                retrieveMessages()
            } catch (ex: Exception) {

            }

        }
    }

    private fun MessageEntity.toMessageUi(): Message {
        return Message(
            id = this.id,
            avatar = this.senderAvatar,
            messageContent = getMessageContent(),
            userName = senderName,
            messageDate = timestamp,
            isMine = isMine
        )
    }

    private fun MessageEntity.getMessageContent(): MessageContent {
        return when (messageType) {
            MessageEntity.MessageType.TEXT -> MessageContent.TextConTent(message)
            MessageEntity.MessageType.IMAGE -> MessageContent.ImageContent(
                message,
                messageDescription
            )
        }
    }

    override fun onCleared() {
        chatJob.cancel()
        GlobalScope.launch {
            disconnectUseCase()
        }
    }
}