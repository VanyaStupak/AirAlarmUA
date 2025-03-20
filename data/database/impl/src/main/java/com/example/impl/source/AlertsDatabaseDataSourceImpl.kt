package com.example.impl.source

import com.example.database.core.model.AlertDatabaseModel
import com.example.database.source.datasource.AlertsDatabaseDataSource
import com.example.impl.source.dao.AlertsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlertsDatabaseDataSourceImpl @Inject constructor(
    private val dao: AlertsDao,
) : AlertsDatabaseDataSource {

    override suspend fun getFlow(): Flow<List<AlertDatabaseModel>?> = dao.getAllAlerts()

    override suspend fun insert(values: List<AlertDatabaseModel>) = dao.insertAlerts(values)

    override suspend fun deleteNotIn(values: List<String>) {
        dao.deleteAlertsNotIn(values)
    }

    override suspend fun deleteAll() {}


}
