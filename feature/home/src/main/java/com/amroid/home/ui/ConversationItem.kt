package com.amroid.home.ui

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amroid.framework.ui.Avatar
import com.amroid.home.ui.model.Conversation

@Composable
fun ConversationItem(item: Conversation) {
    val primaryColor = MaterialTheme.colorScheme.primary
    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Avatar(item.avatarUrl, 50.dp, "avatar")
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(text = item.userName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = item.lastMessage)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = item.lastMessageDate)
            Spacer(modifier = Modifier.height(4.dp))
                Text(item.messageCount.toString(), color = Color.White , fontWeight = FontWeight.Bold, modifier = Modifier
                    .padding(8.dp)
                    .drawBehind {
                        drawCircle(color = primaryColor, radius = 50f)
                    })


        }


    }
}


