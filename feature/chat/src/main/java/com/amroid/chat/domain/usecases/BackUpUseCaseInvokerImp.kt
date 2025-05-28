package com.amroid.chat.domain.usecases

import com.amroid.framework.BackupUseCaseInvoker

class BackUpUseCaseInvokerImp  (private val backUpMessagesUseCase: BackUpMessagesUseCase):BackupUseCaseInvoker {
    override suspend fun invoke() {
        backUpMessagesUseCase()
    }
}