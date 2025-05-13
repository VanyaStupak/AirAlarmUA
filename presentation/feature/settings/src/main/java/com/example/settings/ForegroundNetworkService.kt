package com.example.settings

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.util.Log
import com.example.settings.common.regionKeywords
import dagger.hilt.android.AndroidEntryPoint
import dev.stupak.usecase.usecase.GetActiveAlertsInfoUseCase
import dev.stupak.usecase.usecase.GetAppSettingsUseCase
import dev.stupak.usecase.usecase.GetTelegramMessagesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("NewApi")
@AndroidEntryPoint
class ForegroundNetworkService : Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var getActiveAlertsUseCase: GetActiveAlertsInfoUseCase

    @Inject
    lateinit var getAppSettingsUseCase: GetAppSettingsUseCase

    @Inject
    lateinit var getTgAlerts: GetTelegramMessagesUseCase

    private var wasAlertActive: Boolean = false
    private var newRegion: String = ""

    override fun onCreate() {
        super.onCreate()
        Log.d("Aabababas", "HERE")
        createNotificationChannel()
        serviceScope.launch {
            Log.d("Aabababas", "HERE1")
            performAlertsNetworkRequest()
        }
        serviceScope.launch {
            Log.d("Aabababas", "HERE2")
            performTelegramNetworkRequest()
        }
    }

    private fun updateNotification(
        isAlert: Boolean,
        region: String,
    ) {
        if (isAlert != wasAlertActive) {
            if (isAlert) {
                sendNotification(region, getString(dev.stupak.localisation.R.string.air_raid_alert))
            } else if (region == newRegion) {
                sendNotification(region, getString(dev.stupak.localisation.R.string.air_alert_ended))
            }
            wasAlertActive = isAlert
        }
        newRegion = region

        val message =
            if (isAlert) {
                getString(dev.stupak.localisation.R.string.air_raid_alert_in_area)
            } else {
                getString(dev.stupak.localisation.R.string.no_air_alert)
            }
        startForeground(NOTIFICATION_ID, createNotification(region, message))
    }

    private suspend fun performAlertsNetworkRequest() {
        getActiveAlertsUseCase()
            .collect { result ->
                if (result.isSuccess) {
                    val settings = getAppSettingsUseCase.invoke()
                    val region = settings.getOrNull()?.region ?: ""
                    val alerts = result.getOrNull()
                    val isAlert = alerts?.alerts?.any { it.locationTitle == region } == true
                    updateNotification(isAlert, region)
                }
            }
    }

    private suspend fun performTelegramNetworkRequest() {
        Log.d("Aabababas", "HERE3")
        getTgAlerts()
            .collect { result ->
                Log.d("Aabababas", "result : $result")

                if (result.isSuccess) {
                    val messages = result.getOrNull() ?: emptyList()
                    val settings = getAppSettingsUseCase.invoke()
                    val region = settings.getOrNull()?.region ?: ""
                    val keyword = regionKeywords[region]
                    Log.d("Aabababas", region)
                    val filteredMessages =
                        messages.filter { message ->
                            (keyword != null && message.contains(keyword, ignoreCase = true)) ||
                                message.contains("всій території України", ignoreCase = true)
                        }

                    val notificationText = filteredMessages.joinToString(separator = "\n")

                    if (notificationText.isNotBlank()) {
                        sendNotification("", notificationText)
                    }
                }
            }
    }

    private fun sendNotification(
        title: String,
        message: String,
    ) {
        val notificationManager =
            getSystemService(NotificationManager::class.java) as NotificationManager
        val notification =
            Notification
                .Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(createPendingIntent())
                .setSmallIcon(dev.stupak.ui.R.drawable.ic_warning_circle)
                .setAutoCancel(true)
                .build()
        notificationManager.notify((System.currentTimeMillis() % 10000).toInt(), notification)
    }

    private fun createNotification(
        title: String,
        message: String,
    ): Notification =
        Notification
            .Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(dev.stupak.ui.R.drawable.ic_warning_circle)
            .setContentIntent(createPendingIntent())
            .build()

    private fun createNotificationChannel() {
        val channel =
            NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_LOW,
            )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    private fun createPendingIntent(): PendingIntent {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("myapp://main?page=1")).apply {
                flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        private const val CHANNEL_ID = "foreground_service_channel"
        private const val NOTIFICATION_ID = 1
    }
}
