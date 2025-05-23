package com.amroid.chat.data.datasources

import android.content.Context
import com.amroid.chat.data.model.FirestoreMessageModel
import com.amroid.chat.ui.model.Message
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseMessagingDataSource @Inject constructor(
    private val firestore: FirebaseFireStoreProvider) {
    fun getMessages(chatId: String, userId: String): Flow<FirestoreMessageModel> {
        return callbackFlow {
            val chatRef = getChatRef(chatId)
            val query = chatRef.orderBy("timestamp", Query.Direction.ASCENDING)
            val listenerReigsteration = query.addSnapshotListener { value, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val messages = value?.documents?.mapNotNull {
                    val message = it.toObject(FirestoreMessageModel::class.java)
                    message?.copy(id = it.id)
                } ?: emptyList()
                messages.forEach {
                    try {
                        trySend(it)
                    } catch (e: Exception) {
                        close(e)
                    }
                }


            }
            awaitClose { listenerReigsteration.remove() }


        }

    }

    fun sendMessage(chatId: String, message: FirestoreMessageModel) {
        val chatRf = getChatRef(chatId)
        chatRf.add(message)
    }

    private fun getChatRef(chatId: String) =
        firestore.getFireStore().collection("chats").document(chatId).collection("messages")

}