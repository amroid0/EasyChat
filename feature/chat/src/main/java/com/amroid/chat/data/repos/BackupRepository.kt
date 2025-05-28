package com.amroid.chat.data.repos

import com.amroid.chat.data.datasources.StorageDataSource
import com.amroid.chat.domain.repos.IBackupRepository
import com.amroid.data.database.daos.ConversationDao
import com.amroid.data.database.daos.MessageDao
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import java.io.File

class BackupRepository(
    private val messageDao: MessageDao,
    private val conversationDao: ConversationDao,
    private val storageDataSource: StorageDataSource

) : IBackupRepository {
    override suspend fun backupAllData() {
        val gson = Gson()
        val conversation = conversationDao.getConversationList(1).first()
        for (item in conversation) {
            val messages = messageDao.getAllMessage(item.id)
            val messageString = gson.toJson(messages)
            val file = createTempFile("messages", ".json")
            file.writeText(messageString)
            val remotePath = "conversations/${item.id}/messages.json"
            storageDataSource.uploadFile(file.absolutePath, remotePath)
        }
    }


    private fun createTempFile(prefix: String, sufix: String): File {
        val file = File(System.getProperty("java.io.tmpdir").toString())
        return File.createTempFile(prefix, sufix, file)
    }
}