package com.amroid.chat.domain.usecases

import com.amroid.chat.domain.repos.MessageRepository
import javax.inject.Inject

class DisconnectUseCase @Inject constructor(private val repository: MessageRepository) {
    suspend operator fun invoke() {
        return repository.disconnect()
    }
}