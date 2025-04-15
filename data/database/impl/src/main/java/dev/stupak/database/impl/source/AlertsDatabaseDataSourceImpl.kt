package dev.stupak.database.impl.source

import dev.stupak.database.core.model.AlertDatabaseModel
import dev.stupak.database.impl.source.dao.AlertsDao
import dev.stupak.database.source.datasource.AlertsDatabaseDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlertsDatabaseDataSourceImpl
    @Inject
    constructor(
        private val dao: AlertsDao,
    ) : AlertsDatabaseDataSource {
        override suspend fun getFlow(): Flow<List<AlertDatabaseModel>?> = dao.getAllAlerts()

        override suspend fun insert(values: List<AlertDatabaseModel>) = dao.insertAlerts(values)

        override suspend fun deleteNotIn(values: List<String>) {
            dao.deleteAlertsNotIn(values)
        }

        override suspend fun deleteAll() {}
    }
