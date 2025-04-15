package dev.stupak.repository.impl

import dev.stupak.database.source.datasource.TelegramDatabaseDataSource
import dev.stupak.network.source.datasource.telegram.TelegramNetSource
import dev.stupak.repository.TelegramRepository
import dev.stupak.repository.model.TelegramRepositoryModel
import dev.stupak.repository.model.toTelegramDatabaseModelList
import dev.stupak.repository.model.toTelegramRepositoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TelegramRepositoryImpl
    @Inject
    constructor(
        private val telegramNetSource: TelegramNetSource,
        private val telegramDatabaseDataSource: TelegramDatabaseDataSource,
    ) : TelegramRepository {
        override suspend fun getTgAlerts(): Flow<List<TelegramRepositoryModel>> =
            telegramDatabaseDataSource
                .getFlow()
                .map { list -> list?.map { it.toTelegramRepositoryModel() } ?: emptyList() }

        override suspend fun getTgAlertsFromNet(): List<TelegramRepositoryModel> =
            telegramNetSource.getTgAlerts().toTelegramRepositoryModel()

        override suspend fun insertTelegramMessages(messages: List<TelegramRepositoryModel>) {
            telegramDatabaseDataSource.insert(messages.toTelegramDatabaseModelList())
        }

        override suspend fun deleteTelegramMessages() {
            telegramDatabaseDataSource.deleteAll()
        }
    }
