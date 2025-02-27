package com.amroid.chat.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.amroid.chat.ui.model.Message
import com.amroid.chat.ui.model.MessageContent
import com.amroid.framework.ui.Avatar


@Composable
fun MessageItem(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = if (message.isMine) Arrangement.End else Arrangement.Start
    ) {
        if (message.isMine) {
            Avatar(message.avatar, size = 50.dp, message.id)
            Spacer(Modifier.width(4.dp))
        }
        Column {
            Text(text = message.userName, fontWeight = FontWeight.Bold)

            when (message.messageContent) {
                is MessageContent.ImageContent -> {
                    AsyncImage(
                        model = message.messageContent.url,
                        contentDescription = message.messageContent.contentDescription,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                is MessageContent.TextConTent -> {
                    Surface(
                        color = if (message.isMine)
                            Color.Green
                        else MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            message.messageContent.text,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                                    color = MaterialTheme.colorScheme.onPrimary
                        )

                    }
                }
            }
            Text(text = message.messageDate)
        }

    }
}