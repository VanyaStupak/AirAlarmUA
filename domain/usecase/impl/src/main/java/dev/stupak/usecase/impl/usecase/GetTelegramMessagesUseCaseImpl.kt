package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.TelegramRepository
import dev.stupak.usecase.usecase.GetTelegramMessagesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTelegramMessagesUseCaseImpl
    @Inject
    constructor(
        private val telegramRepository: TelegramRepository,
    ) : GetTelegramMessagesUseCase {
        override suspend operator fun invoke(): Flow<Result<List<String>>> =
            telegramRepository
                .getTgAlerts()
                .map { tgMessageEntities ->
                    Result.success(tgMessageEntities.map { it.message })
                }.catch { exception ->
                    emit(Result.failure(exception))
                }
    }
