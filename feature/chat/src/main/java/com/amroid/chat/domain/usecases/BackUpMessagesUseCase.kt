package com.amroid.chat.domain.usecases

import com.amroid.chat.domain.repos.IBackupRepository

class BackUpMessagesUseCase(private val iBackupRepository: IBackupRepository) {

    suspend operator fun invoke() {
        iBackupRepository.backupAllData()
    }
}