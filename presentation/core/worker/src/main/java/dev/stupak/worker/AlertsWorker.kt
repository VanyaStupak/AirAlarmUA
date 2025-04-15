package dev.stupak.worker

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.stupak.usecase.model.DomainAlertsList
import dev.stupak.usecase.usecase.DeleteActiveAlertsInfoUseCase
import dev.stupak.usecase.usecase.DeleteTelegramMessagesUseCase
import dev.stupak.usecase.usecase.GetActiveAlertsInfoFromNetUseCase
import dev.stupak.usecase.usecase.GetAppSettingsUseCase
import dev.stupak.usecase.usecase.GetTelegramMessagesFromNetUseCase
import dev.stupak.usecase.usecase.InsertActiveAlertsInfoUseCase
import dev.stupak.usecase.usecase.InsertTelegramMessagesUseCase

@HiltWorker
class AlertsWorker
    @AssistedInject
    constructor(
        @Assisted context: Context,
        @Assisted workerParams: WorkerParameters,
        private val getAppSettingsUseCase: GetAppSettingsUseCase,
        private val insertActiveAlertsInfoUseCase: InsertActiveAlertsInfoUseCase,
        private val deleteActiveAlertsInfoUseCase: DeleteActiveAlertsInfoUseCase,
        private val insertTelegramMessagesUseCase: InsertTelegramMessagesUseCase,
        private val deleteTelegramMessagesUseCase: DeleteTelegramMessagesUseCase,
        private val getTelegramMessagesFromNetUseCase: GetTelegramMessagesFromNetUseCase,
        private val getAlertsInfoFromNetUseCase: GetActiveAlertsInfoFromNetUseCase,
    ) : CoroutineWorker(context, workerParams) {
        private val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences("alerts_prefs", Context.MODE_PRIVATE)
        private val gson = Gson()

        override suspend fun doWork(): Result =
            try {
                val appSettings = getAppSettingsUseCase().getOrNull()
                val alertsList = getAlertsInfoFromNetUseCase().getOrNull()
                if (appSettings?.telegramNotifications == true) {
                    val telegramMessages = getTelegramMessagesFromNetUseCase().getOrNull()
                    deleteTelegramMessagesUseCase()
                    Log.d("Aabababas", telegramMessages.toString())
                    insertTelegramMessagesUseCase(messages = telegramMessages ?: emptyList())
                }

                val newTitles = alertsList?.alerts?.map { it.locationTitle }
                deleteActiveAlertsInfoUseCase(newTitles ?: emptyList())
                insertActiveAlertsInfoUseCase(alertsList ?: DomainAlertsList(emptyList()))

                val alertsJson = gson.toJson(alertsList?.alerts)
                sharedPreferences
                    .edit()
                    .putString("alerts_list", alertsJson)
                    .putString("region_name", appSettings?.region)
                    .apply()

                Result.success()
            } catch (e: Exception) {
                Result.retry()
            }
    }
