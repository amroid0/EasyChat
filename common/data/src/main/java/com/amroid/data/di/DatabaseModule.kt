package com.amroid.data.di

import android.content.Context
import com.amroid.data.database.ChatDatabase
import com.amroid.data.database.daos.ConversationDao
import com.amroid.data.database.daos.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideChatDatabase(@ApplicationContext context: Context): ChatDatabase {
        return ChatDatabase.getDatabase(context)
    }

    @Provides
    fun provideMessageDao(chatDatabase: ChatDatabase): MessageDao {
        return chatDatabase.provideMessageDao()
    }

    @Provides
    fun provideConversationDao(chatDatabase: ChatDatabase): ConversationDao {
        return chatDatabase.provideConversationDao()
    }
}