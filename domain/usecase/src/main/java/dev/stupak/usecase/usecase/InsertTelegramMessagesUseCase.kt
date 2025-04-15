package dev.stupak.usecase.usecase

interface InsertTelegramMessagesUseCase {
    suspend operator fun invoke(messages: List<String>)
}
