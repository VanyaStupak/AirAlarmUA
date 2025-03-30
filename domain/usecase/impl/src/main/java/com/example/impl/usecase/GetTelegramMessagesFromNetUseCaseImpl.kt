package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.usecase.usecase.GetTelegramMessagesFromNetUseCase
import dev.stupak.repository.TelegramRepository
import javax.inject.Inject

class GetTelegramMessagesFromNetUseCaseImpl
@Inject
constructor(
    private val telegramRepository: TelegramRepository
) : GetTelegramMessagesFromNetUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(): Result<List<String>> {
        return runCatching {
            telegramRepository.getTgAlertsFromNet().map { it.message }
        }
    }
}

