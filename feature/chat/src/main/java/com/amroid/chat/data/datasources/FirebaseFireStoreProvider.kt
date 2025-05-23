package com.amroid.chat.data.datasources

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseFireStoreProvider(private val context: Context) {
    init {
        FirebaseApp.initializeApp(context)

    }
    fun getFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}