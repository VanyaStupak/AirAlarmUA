package dev.stupak.repository

import dev.stupak.repository.model.RepositoryAlertsList
import kotlinx.coroutines.flow.Flow

interface AlertsRepository {
    fun getActiveAlertsInfo(): Flow<RepositoryAlertsList>

    suspend fun getAlertsForPeriod(uid:Int = 31,period:String = "month_ago"): RepositoryAlertsList
}
