package com.example.worker

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.usecase.model.DomainAlertsList
import com.example.usecase.usecase.DeleteActiveAlertsInfoUseCase
import com.example.usecase.usecase.DeleteTelegramMessagesUseCase
import com.example.usecase.usecase.GetActiveAlertsInfoFromNetUseCase
import com.example.usecase.usecase.GetAppSettingsUseCase
import com.example.usecase.usecase.GetTelegramMessagesFromNetUseCase
import com.example.usecase.usecase.InsertActiveAlertsInfoUseCase
import com.example.usecase.usecase.InsertTelegramMessagesUseCase
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class AlertsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getAppSettingsUseCase: GetAppSettingsUseCase,
    private val insertActiveAlertsInfoUseCase: InsertActiveAlertsInfoUseCase,
    private val deleteActiveAlertsInfoUseCase: DeleteActiveAlertsInfoUseCase,
    private val insertTelegramMessagesUseCase: InsertTelegramMessagesUseCase,
    private val deleteTelegramMessagesUseCase: DeleteTelegramMessagesUseCase,
    private val getTelegramMessagesFromNetUseCase: GetTelegramMessagesFromNetUseCase,
    private val getAlertsInfoFromNetUseCase: GetActiveAlertsInfoFromNetUseCase
) : CoroutineWorker(context, workerParams) {

    private val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences("alerts_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        return try {
            val appSettings = getAppSettingsUseCase().getOrNull()
            val alertsList = getAlertsInfoFromNetUseCase().getOrNull()
            if(appSettings?.telegramNotifications == true) {
                val telegramMessages = getTelegramMessagesFromNetUseCase().getOrNull()
                deleteTelegramMessagesUseCase()
                insertTelegramMessagesUseCase(messages = telegramMessages ?: emptyList())
            }

            val newTitles = alertsList?.alerts?.map { it.locationTitle }
            deleteActiveAlertsInfoUseCase(newTitles ?: emptyList())
            insertActiveAlertsInfoUseCase(alertsList ?: DomainAlertsList(emptyList()))

            val alertsJson = gson.toJson(alertsList?.alerts)
            sharedPreferences.edit()
                .putString("alerts_list", alertsJson)
                .putString("region_name", appSettings?.region).apply()

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}



