package dev.stupak.repository

import dev.stupak.repository.model.RepositoryAlertsList
import kotlinx.coroutines.flow.Flow

interface AlertsRepository {
    suspend fun getActiveAlertsInfo(): Flow<RepositoryAlertsList>

    suspend fun getActiveAlertsInfoFromNet(): RepositoryAlertsList

    suspend fun getAlertsForPeriod(
        uid: Int = 31,
        period: String = "month_ago",
    ): RepositoryAlertsList

    suspend fun insertActiveAlertsInfo(alerts: RepositoryAlertsList)

    suspend fun deleteActiveAlertsInfo(titles: List<String>)
}
