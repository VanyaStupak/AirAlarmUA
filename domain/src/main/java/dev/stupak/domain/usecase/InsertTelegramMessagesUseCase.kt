package dev.stupak.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import dev.stupak.domain.model.DomainAlertsList
import dev.stupak.domain.model.toDomainModel
import dev.stupak.domain.model.toRepositoryAlertsList
import dev.stupak.repository.AlertsRepository
import dev.stupak.repository.TelegramRepository
import dev.stupak.repository.model.toTelegramRepositoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InsertTelegramMessagesUseCase
@Inject
constructor(
    private val telegramRepository: TelegramRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(messages: List<String>) {
        return telegramRepository.insertTelegramMessages(messages.toTelegramRepositoryModel())
    }
}
