package com.amroid.data

import com.google.firebase.messaging.FirebaseMessaging

class FCMTokenDataSource(private val firebaseMessaging: FirebaseMessaging = FirebaseMessaging.getInstance()) {
    fun getToken(): String = firebaseMessaging.token.result
}