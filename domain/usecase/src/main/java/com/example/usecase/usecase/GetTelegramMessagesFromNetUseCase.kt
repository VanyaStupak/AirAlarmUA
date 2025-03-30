package com.example.usecase.usecase

interface GetTelegramMessagesFromNetUseCase {
    suspend operator fun invoke(): Result<List<String>>
}