package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.usecase.usecase.GetTelegramMessagesUseCase
import dev.stupak.repository.TelegramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTelegramMessagesUseCaseImpl
@Inject
constructor(
    private val telegramRepository: TelegramRepository
) : GetTelegramMessagesUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(): Flow<Result<List<String>>> {
        return telegramRepository.getTgAlerts()
            .map { tgMessageEntities ->
                Result.success(tgMessageEntities.map { it.message })
            }
            .catch { exception ->
                emit(Result.failure(exception))
            }
    }
}

