package dev.stupak.repository.impl

import com.example.database.dao.TelegramDao
import dev.stupak.repository.TelegramRepository
import dev.stupak.repository.model.TelegramRepositoryModel
import dev.stupak.repository.model.toTelegramRepositoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TelegramRepositoryImpl
@Inject
constructor(
    private val telegramDao: TelegramDao,
): TelegramRepository {
    override suspend fun getTgAlerts(): Flow<List<TelegramRepositoryModel>> {
        return telegramDao.getAllMessages()
            .map { list -> list.map { it.toTelegramRepositoryModel() } }
    }
}