package com.amroid.chat.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object WebSocketClient {
    val client = HttpClient {
        install(WebSockets) {
            contentConverter =
                KotlinxWebsocketSerializationConverter(Json)
        }
    }
}
object ApiHttpClient{
    val client =  HttpClient{
        install(ContentNegotiation){
            json()
        }
    }
}