package com.amroid.chat.data.repos

import com.amroid.chat.data.datasources.LocalMessagingDataSource
import com.amroid.chat.data.datasources.WebSocketMessagingDataSource
import com.amroid.chat.data.mappers.LocalMessageDataMapper
import com.amroid.chat.data.mappers.LocalMessageDomainMapper
import com.amroid.chat.data.mappers.WebSocketDomainMapper
import com.amroid.chat.data.mappers.WebSocketMessageDataMapper
import com.amroid.chat.domain.entities.MessageEntity
import com.amroid.chat.domain.repos.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WebsocketMessageRepository @Inject constructor(
    val webSocketMessagingDataSource: WebSocketMessagingDataSource,
    val localMessagingDataSource: LocalMessagingDataSource,
    val webSocketDomainMapper: WebSocketDomainMapper,
    val webSocketMessageDataMapper: WebSocketMessageDataMapper,
    val localMessageDataMapper: LocalMessageDataMapper,
    val localMessageDomainMapper: LocalMessageDomainMapper
) :
    MessageRepository {
    override suspend fun getMessages(chatId: String, userId: String): Flow<MessageEntity> {
        return flow<MessageEntity> {
            try {


                webSocketMessagingDataSource.connect().collect { message ->
                    val domainMessage = webSocketDomainMapper.map("", message)
                    localMessagingDataSource.insertMessage(
                        localMessageDataMapper.map(
                            "",
                            domainMessage
                        )
                    )
                    emit(domainMessage)
                }

            } catch (ex: Exception) {
                localMessagingDataSource.getMessagesByConversationId(chatId).collect {
                    it.forEach { message ->
                        emit(localMessageDomainMapper.map("", message))
                    }
                }

            }
        }

    }


    override suspend fun sendMessages(chatId: String, message: MessageEntity) {
        localMessagingDataSource.insertMessage(localMessageDataMapper.map("", message))
        webSocketMessagingDataSource.sendMessage(webSocketMessageDataMapper.map("", message))

    }

    override suspend fun disconnect() {
        return webSocketMessagingDataSource.disconnect()

    }
}