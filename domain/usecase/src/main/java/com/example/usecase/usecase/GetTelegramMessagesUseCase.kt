package com.example.usecase.usecase

import kotlinx.coroutines.flow.Flow

interface GetTelegramMessagesUseCase {
    suspend operator fun invoke(): Flow<Result<List<String>>>
}