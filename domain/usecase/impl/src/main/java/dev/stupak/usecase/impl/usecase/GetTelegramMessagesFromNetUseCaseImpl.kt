package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.TelegramRepository
import dev.stupak.usecase.resultLauncher
import dev.stupak.usecase.usecase.GetTelegramMessagesFromNetUseCase
import javax.inject.Inject

class GetTelegramMessagesFromNetUseCaseImpl
    @Inject
    constructor(
        private val telegramRepository: TelegramRepository,
    ) : GetTelegramMessagesFromNetUseCase {
        override suspend operator fun invoke(): Result<List<String>> =
            resultLauncher {
                telegramRepository.getTgAlertsFromNet().map { it.message }
            }
    }
