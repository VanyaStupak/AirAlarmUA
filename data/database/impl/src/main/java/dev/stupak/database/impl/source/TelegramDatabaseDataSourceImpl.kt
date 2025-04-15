package dev.stupak.database.impl.source

import dev.stupak.database.core.model.TelegramDatabaseModel
import dev.stupak.database.impl.source.dao.TelegramDao
import dev.stupak.database.source.datasource.TelegramDatabaseDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TelegramDatabaseDataSourceImpl
    @Inject
    constructor(
        private val dao: TelegramDao,
    ) : TelegramDatabaseDataSource {
        override suspend fun getFlow(): Flow<List<TelegramDatabaseModel>?> = dao.getAllMessages()

        override suspend fun insert(values: List<TelegramDatabaseModel>) {
            dao.insertMessages(values)
        }

        override suspend fun deleteNotIn(values: List<String>) {}

        override suspend fun deleteAll() {
            dao.deleteAllMessage()
        }
    }
