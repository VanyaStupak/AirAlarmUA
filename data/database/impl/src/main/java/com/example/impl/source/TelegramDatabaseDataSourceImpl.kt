package com.example.impl.source

import com.example.database.core.model.TelegramDatabaseModel
import com.example.database.source.datasource.TelegramDatabaseDataSource
import com.example.impl.source.dao.TelegramDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TelegramDatabaseDataSourceImpl @Inject constructor(
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
