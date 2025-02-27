package com.amroid.chat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amroid.chat.ui.model.Message
import com.amroid.chat.ui.model.MessageContent
import com.amroid.chat.ui.widgets.MessageItem
import com.amroid.chat.ui.widgets.SendBoxWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(chatId: String?, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Chat With") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = { IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, "back")
                } },


            )
        },
        bottomBar = {
            SendBoxWidget()
        }


    ) { contnetPadding ->
        MessageList(Modifier.padding(contnetPadding), getFakeMessages())
    }
}

@Composable
fun MessageList(modifier: Modifier, messages: List<Message>) {
    LazyColumn(modifier = modifier) {
        items(messages) {
            MessageItem(it)
        }
    }
}
fun getFakeMessages() = listOf(
    Message(id = "1", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:00 PM",isMine = false),
    Message(id = "2", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:01 PM",isMine = true),
    Message(id = "3", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("How are you "), userName = "Amr", messageDate = "02:02 PM",isMine = false),
    Message(id = "4", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Fine, You? "), userName = "Amr", messageDate = "02:02 PM",isMine = true),
    Message(id = "5", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Good "), userName = "Amr", messageDate = "02:03 PM",isMine = false),
    Message(id = "6", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:04 PM",isMine = false),
    Message(id = "7", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:00 PM",isMine = false),
    Message(id = "8", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:00 PM",isMine = false),
    Message(id = "9", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:00 PM",isMine = false),
    Message(id = "10", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:00 PM",isMine = false),
    Message(id = "11", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:00 PM",isMine = false),
    Message(id = "12", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:00 PM",isMine = false),
    Message(id = "13", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:00 PM",isMine = false),
    Message(id = "14", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:00 PM",isMine = false),
    Message(id = "15", avatar = "https://i.pravatar.cc/300?img=1", messageContent = MessageContent.TextConTent("Hello "), userName = "Amr", messageDate = "02:00 PM",isMine = false),

)




