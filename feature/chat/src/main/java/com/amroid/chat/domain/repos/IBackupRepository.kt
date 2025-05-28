package com.amroid.chat.domain.repos

interface IBackupRepository {
    suspend fun backupAllData()
}