package dev.stupak.repository.impl

import dev.stupak.network.service.TelegramService
import dev.stupak.repository.TelegramRepository
import javax.inject.Inject

class TelegramRepositoryImpl
@Inject
constructor(
    private val telegramService: TelegramService,
): TelegramRepository {
    override suspend fun getTgAlerts(): List<String> {
        return telegramService.getTgAlerts()
    }
}