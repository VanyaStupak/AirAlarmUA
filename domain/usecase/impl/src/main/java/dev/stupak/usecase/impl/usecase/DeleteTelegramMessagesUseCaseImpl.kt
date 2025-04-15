package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.TelegramRepository
import dev.stupak.usecase.usecase.DeleteTelegramMessagesUseCase
import javax.inject.Inject

class DeleteTelegramMessagesUseCaseImpl
    @Inject
    constructor(
        private val telegramRepository: TelegramRepository,
    ) : DeleteTelegramMessagesUseCase {
        override suspend operator fun invoke() {
            telegramRepository.deleteTelegramMessages()
        }
    }
