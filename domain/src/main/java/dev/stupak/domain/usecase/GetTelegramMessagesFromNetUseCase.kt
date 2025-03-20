package dev.stupak.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import dev.stupak.repository.TelegramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTelegramMessagesFromNetUseCase
@Inject
constructor(
    private val telegramRepository: TelegramRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): Result<List<String>> {
        return runCatching {
            telegramRepository.getTgAlertsFromNet().map { it.message }
        }

    }
}

