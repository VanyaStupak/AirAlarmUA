package dev.stupak.worker

import android.content.Context
import android.content.SharedPreferences
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.database.dao.AlertsDao
import com.example.database.dao.TelegramDao
import com.example.local.settings.AppSettingsDataStore
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.stupak.network.service.AlertsApiService
import dev.stupak.network.service.TelegramService
import dev.stupak.worker.mapper.toAlertsEntityList
import dev.stupak.worker.mapper.toTgMessageEntityList
import kotlinx.coroutines.flow.first

@HiltWorker
class AlertsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val alertsApiService: AlertsApiService,
    private val telegramService: TelegramService,
    private val alertsDao: AlertsDao,
    private val telegramDao: TelegramDao,
    private val dataStore: AppSettingsDataStore
) : CoroutineWorker(context, workerParams) {

    private val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences("alerts_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    override suspend fun doWork(): Result {
        return try {
            val appSettings = dataStore.appSettings.first()
            val alertsList = alertsApiService.getActiveAlertsInfo()
            if(appSettings.telegramNotifications) {
                val telegramMessages = telegramService.getTgAlerts()
                telegramDao.deleteAllMessage()
                telegramDao.insertMessages(messages = telegramMessages.toTgMessageEntityList())
            }

            val newTitles = alertsList.toAlertsEntityList().map { it.locationTitle }
            alertsDao.deleteAlertsNotIn(newTitles)
            alertsDao.insertAlerts(alerts = alertsList.toAlertsEntityList())

            val alertsJson = gson.toJson(alertsList.alerts)
            sharedPreferences.edit()
                .putString("alerts_list", alertsJson)
                .putString("region_name", appSettings.region).apply()

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}



