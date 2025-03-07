package dev.stupak.repository.impl

import com.example.database.dao.AlertsDao
import dev.stupak.network.service.AlertsApiService
import dev.stupak.repository.AlertsRepository
import dev.stupak.repository.model.RepositoryAlertsList
import dev.stupak.repository.model.toRepositoryList
import dev.stupak.repository.model.toRepositoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlertsRepositoryImpl @Inject constructor(
    private val alertsApiService: AlertsApiService,
    private val alertsDao: AlertsDao
) : AlertsRepository{
    override fun getActiveAlertsInfo(): Flow<RepositoryAlertsList> {
        return alertsDao.getAllAlerts().map { it.toRepositoryList() }
    }

    override suspend fun getAlertsForPeriod(uid: Int, period: String): RepositoryAlertsList {
        return alertsApiService.getAlertsForPeriod(uid,period).toRepositoryModel()
    }

}
