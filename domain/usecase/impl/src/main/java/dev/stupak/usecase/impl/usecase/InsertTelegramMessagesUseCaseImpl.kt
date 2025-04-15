package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.TelegramRepository
import dev.stupak.repository.model.toTelegramRepositoryModel
import dev.stupak.usecase.usecase.InsertTelegramMessagesUseCase
import javax.inject.Inject

class InsertTelegramMessagesUseCaseImpl
    @Inject
    constructor(
        private val telegramRepository: TelegramRepository,
    ) : InsertTelegramMessagesUseCase {
        override suspend operator fun invoke(messages: List<String>) {
            telegramRepository.insertTelegramMessages(messages.toTelegramRepositoryModel())
        }
    }
