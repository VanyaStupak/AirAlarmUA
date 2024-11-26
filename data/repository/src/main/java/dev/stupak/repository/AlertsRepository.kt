package dev.stupak.repository

import dev.stupak.repository.model.RepositoryAlertsList

interface AlertsRepository {
    suspend fun getActiveAlertsInfo(): Result<RepositoryAlertsList>

    suspend fun getAlertsForPeriod(uid:Int = 31,period:String = "month_ago"): Result<RepositoryAlertsList>
}
