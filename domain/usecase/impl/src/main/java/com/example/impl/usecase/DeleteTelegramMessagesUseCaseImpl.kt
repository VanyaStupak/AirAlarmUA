package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.usecase.usecase.DeleteTelegramMessagesUseCase
import dev.stupak.repository.TelegramRepository
import javax.inject.Inject

class DeleteTelegramMessagesUseCaseImpl
@Inject
constructor(
    private val telegramRepository: TelegramRepository
) : DeleteTelegramMessagesUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke() {
        telegramRepository.deleteTelegramMessages()
    }
}
