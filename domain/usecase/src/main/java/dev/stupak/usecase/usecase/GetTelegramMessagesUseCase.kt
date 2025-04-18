package dev.stupak.usecase.usecase

import kotlinx.coroutines.flow.Flow

interface GetTelegramMessagesUseCase {
    suspend operator fun invoke(): Flow<Result<List<String>>>
}
