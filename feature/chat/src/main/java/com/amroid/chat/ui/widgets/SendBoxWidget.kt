package com.amroid.chat.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SendBoxWidget() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            text,
            onValueChange = { text = it },
            modifier = Modifier
                .height(60.dp)
                .align(Alignment.CenterStart)
                .fillMaxWidth(.85f)
        )
        IconButton(
            onClick = {}, modifier = Modifier
                .height(60.dp)
                .align(Alignment.CenterEnd)
        ) {
            Icon(Icons.Default.Send, "send", tint = MaterialTheme.colorScheme.primary)
        }
    }
}