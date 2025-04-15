package dev.stupak.repository.impl

import dev.stupak.database.source.datasource.AlertsDatabaseDataSource
import dev.stupak.network.source.datasource.alerts.AlertsNetSource
import dev.stupak.repository.AlertsRepository
import dev.stupak.repository.model.RepositoryAlertsList
import dev.stupak.repository.model.toAlertEntityList
import dev.stupak.repository.model.toRepositoryList
import dev.stupak.repository.model.toRepositoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlertsRepositoryImpl
    @Inject
    constructor(
        private val alertsNetSource: AlertsNetSource,
        private val alertsDatabaseDataSource: AlertsDatabaseDataSource,
    ) : AlertsRepository {
        override suspend fun getActiveAlertsInfo(): Flow<RepositoryAlertsList> =
            alertsDatabaseDataSource
                .getFlow()
                .map { it?.toRepositoryList() ?: RepositoryAlertsList(emptyList()) }

        override suspend fun getActiveAlertsInfoFromNet(): RepositoryAlertsList = alertsNetSource.getActiveAlertsInfo().toRepositoryModel()

        override suspend fun getAlertsForPeriod(
            uid: Int,
            period: String,
        ): RepositoryAlertsList = alertsNetSource.getAlertsForPeriod(uid, period).toRepositoryModel()

        override suspend fun insertActiveAlertsInfo(alerts: RepositoryAlertsList) {
            alertsDatabaseDataSource.insert(alerts.toAlertEntityList())
        }

        override suspend fun deleteActiveAlertsInfo(titles: List<String>) {
            alertsDatabaseDataSource.deleteNotIn(titles)
        }
    }
