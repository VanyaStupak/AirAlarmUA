package dev.stupak.usecase.usecase

interface GetTelegramMessagesFromNetUseCase {
    suspend operator fun invoke(): Result<List<String>>
}
