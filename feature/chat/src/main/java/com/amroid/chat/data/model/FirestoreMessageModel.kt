package com.amroid.chat.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Transient

data class FirestoreMessageModel(
    @Transient
    val id: String = "",
    @get:PropertyName("senderId")
    @set:PropertyName("senderId")
    var senderId: String = "",
    @get:PropertyName("senderName")
    @set:PropertyName("senderName")
    var senderName: String = "",
    @get:PropertyName("senderAvatar")
    @set:PropertyName("senderAvatar")
    var senderAvatar: String = "",
    @get:PropertyName("content")
    @set:PropertyName("content")
    var content: String = "",
    @get :PropertyName("timestamp")
    @set :PropertyName("timestamp")
    var timeStamp: Timestamp = Timestamp.now(),
    @get :PropertyName("conversationId")
    @set :PropertyName("conversationId")
    var conversationId: String,


    )