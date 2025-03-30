package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.usecase.usecase.InsertTelegramMessagesUseCase
import dev.stupak.repository.TelegramRepository
import dev.stupak.repository.model.toTelegramRepositoryModel
import javax.inject.Inject

class InsertTelegramMessagesUseCaseImpl
@Inject
constructor(
    private val telegramRepository: TelegramRepository
) : InsertTelegramMessagesUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(messages: List<String>) {
        telegramRepository.insertTelegramMessages(messages.toTelegramRepositoryModel())
    }
}
