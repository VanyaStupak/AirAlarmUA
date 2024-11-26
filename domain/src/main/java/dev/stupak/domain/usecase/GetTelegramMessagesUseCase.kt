package dev.stupak.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import dev.stupak.repository.TelegramRepository
import javax.inject.Inject

class GetTelegramMessagesUseCase
    @Inject
    constructor(
        private val telegramRepository: TelegramRepository
    ) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): List<String>{
        return telegramRepository.getTgAlerts()
    }
}