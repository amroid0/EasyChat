package com.amroid.chat.di

import android.content.Context
import com.amroid.chat.data.ApiHttpClient
import com.amroid.chat.data.WebSocketClient
import com.amroid.chat.data.datasources.FirebaseFireStoreProvider
import com.amroid.chat.data.mappers.MessageDataMapper
import com.amroid.chat.data.mappers.MessageDomainMapper
import com.amroid.chat.data.mappers.UserScopedMapper
import com.amroid.chat.data.model.FirestoreMessageModel
import com.amroid.chat.data.repos.ChatRoomRepositoryImp
import com.amroid.chat.data.repos.FirebaseMessageRepository
import com.amroid.chat.data.repos.WebsocketMessageRepository
import com.amroid.chat.domain.entities.MessageEntity
import com.amroid.chat.domain.repos.ChatRoomRepository
import com.amroid.chat.domain.repos.MessageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ChatModule {
    companion object {
        private const val WEB_SOCKET_URL =
            "wss://s14350.nyc1.piesocket.com/v3/1?api_key=Gp3HKBSIEQDcHFakG7CtWsPdQnT8QI6Y89O4Yu9f&notify_self=1"
        private const val API_URL = "http://whatspackt.com/chats/%s"
        const val WEB_SOCKET_HTTP_CLIENT = "WEB_SOCKET_HTTP_CLIENT"
        const val API_HTTP_CLIENT = "API_HTTP_CLIENT"

        @Provides
        @Named(WEB_SOCKET_HTTP_CLIENT)
        fun provideWebSocketUrl(): String {
            return WEB_SOCKET_URL
        }

        @Provides
        @Named(API_HTTP_CLIENT)
        fun provideApiClientUrl(): String {
            return API_URL
        }

        @Provides
        @Named(WEB_SOCKET_HTTP_CLIENT)
        fun provideWebsocketHttpClient(): HttpClient {
            return WebSocketClient.client
        }

        @Provides
        @Named(API_HTTP_CLIENT)
        fun provideApiHttpClient(): HttpClient {
            return ApiHttpClient.client
        }


        @Provides
        @Singleton
        fun provideFirebaseProvider(@ApplicationContext context: Context): FirebaseFireStoreProvider {
            return FirebaseFireStoreProvider(context)
        }



    }

    @Binds
    abstract fun bindMessageDomainMapper(
        impl: MessageDomainMapper
    ): UserScopedMapper<FirestoreMessageModel, MessageEntity>

    @Binds
    abstract fun bindMessageDataMapper(
        impl: MessageDataMapper
    ): UserScopedMapper<MessageEntity, FirestoreMessageModel>

    @Binds
    abstract fun provideMessageRepository(websocketMessageRepository: FirebaseMessageRepository): MessageRepository

    @Binds
    abstract fun provideChatRoomRepository(chatRoomRepositoryImp: ChatRoomRepositoryImp): ChatRoomRepository
}