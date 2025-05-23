package com.amroid.chat.data.datasources

import android.util.Log
import com.amroid.chat.data.model.WebsocketMessageModel
import com.amroid.chat.di.ChatModule.Companion.WEB_SOCKET_HTTP_CLIENT
import com.amroid.chat.domain.entities.MessageEntity
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.converter
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.http.Url
import io.ktor.serialization.deserialize
import io.ktor.serialization.serialize
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class WebSocketMessagingDataSource @Inject constructor(
    @Named(WEB_SOCKET_HTTP_CLIENT) private val httpClient: HttpClient,
    @Named(WEB_SOCKET_HTTP_CLIENT) private val urlString: String
) {
    private lateinit var webSocketSession: DefaultClientWebSocketSession
    suspend fun connect(): Flow<WebsocketMessageModel> =
        httpClient.webSocketSession { url(urlString) }
            .also { webSocketSession = it }
            .incoming.receiveAsFlow()
            .mapNotNull { frame -> handleMessage(frame)}
            .retryWhen { cause, attempt ->
                if (cause is IOException && attempt < 4) {
                    delay(1000)
                    true
                } else false
            }
            .catch { e -> Log.e(TAG, "WebSocket Flow error", e) }

    companion object{
        const val TAG = "sokect"
    }

    public suspend fun sendMessage(message: WebsocketMessageModel) {
        webSocketSession.converter?.serialize(message)?.let { webSocketSession.send(it) }
    }

     suspend fun disconnect() {
        webSocketSession.close(CloseReason(code = CloseReason.Codes.NORMAL, "Disconnected"))
    }

    private suspend fun handleMessage(frame: Frame): WebsocketMessageModel? {
        return when (frame) {
            is Frame.Text -> webSocketSession.converter?.deserialize(content = frame)
            is Frame.Close -> {
                disconnect()
                null
            }

            else -> null
        }
    }


}