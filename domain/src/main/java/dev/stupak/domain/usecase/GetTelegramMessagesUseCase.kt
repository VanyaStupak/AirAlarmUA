package dev.stupak.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import dev.stupak.repository.TelegramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTelegramMessagesUseCase
@Inject
constructor(
    private val telegramRepository: TelegramRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): Flow<Result<List<String>>> {
        return telegramRepository.getTgAlerts()
            .map { tgMessageEntities ->
                Result.success(tgMessageEntities.map { it.message })
            }
            .catch { exception ->
                emit(Result.failure(exception))
            }
    }
}

