package com.amroid.chat.data.datasources

import com.amroid.chat.data.model.ChatRoomInfo
import com.amroid.chat.di.ChatModule.Companion.API_HTTP_CLIENT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Named

class ChatRoomDataSource @Inject constructor(
    @Named(API_HTTP_CLIENT) private val httpClient: HttpClient,
    @Named(API_HTTP_CLIENT) private val url: String
) {
    suspend fun getChatRoomInfo(id: String): ChatRoomInfo {
        return httpClient.get(url.format(id)).body()
    }
}