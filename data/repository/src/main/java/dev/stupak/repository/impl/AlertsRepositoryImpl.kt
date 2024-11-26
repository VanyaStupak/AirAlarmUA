package dev.stupak.repository.impl

import dev.stupak.network.service.AlertsApiService
import dev.stupak.repository.AlertsRepository
import dev.stupak.repository.model.RepositoryAlertsList
import dev.stupak.repository.model.toRepositoryModel
import javax.inject.Inject

class AlertsRepositoryImpl
    @Inject
    constructor(
        private val alertsApiService: AlertsApiService,
) : AlertsRepository {
    override suspend fun getActiveAlertsInfo(): Result<RepositoryAlertsList> {
        return try {
            val result = alertsApiService.getActiveAlertsInfo()
            val data = result.getOrNull()
            if (data != null) {
                Result.success(data.toRepositoryModel())
            } else {
                Result.failure(IllegalStateException("No data available"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }


    override suspend fun getAlertsForPeriod(
        uid: Int,
        period: String
    ): Result<RepositoryAlertsList> {
        return try {
            val result = alertsApiService.getAlertsForPeriod(uid,period)
            val data = result.getOrNull()
            if (data != null) {
                Result.success(data.toRepositoryModel())
            } else {
                Result.failure(IllegalStateException("No data available"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }


}
