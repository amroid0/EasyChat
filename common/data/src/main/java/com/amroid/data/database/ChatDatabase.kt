package com.amroid.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amroid.data.database.daos.ConversationDao
import com.amroid.data.database.daos.MessageDao
import com.amroid.data.database.entities.ConversationDbEntity
import com.amroid.data.database.entities.MessageDbEntity

@Database(entities = [MessageDbEntity::class, ConversationDbEntity::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun provideMessageDao(): MessageDao
    abstract fun provideConversationDao(): ConversationDao

    companion object {
        @Volatile
        private var INSTANCE: ChatDatabase? = null
        fun getDatabase(context: Context): ChatDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = ChatDatabase::class.java,
                    name = "chat_database"
                ).build()
                INSTANCE = instance
                instance

            }
        }
    }
}