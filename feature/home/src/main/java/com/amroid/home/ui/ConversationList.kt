package com.amroid.home.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amroid.home.ui.model.Conversation

@Composable
fun ConversationList(list: List<Conversation>) {
    LazyColumn(

    ) {
        items(list) {
            ConversationItem(it)
        }

    }
}