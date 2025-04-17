package dev.stupak.host

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.settings.ForegroundNetworkService
import com.example.widget.WidgetUpdateWorker
import dagger.hilt.android.AndroidEntryPoint
import dev.stupak.navigation.NavHost
import dev.stupak.ui.AirAlarmUATheme
import dev.stupak.usecase.model.SettingsDomainModel
import dev.stupak.usecase.usecase.GetAppSettingsUseCase
import dev.stupak.usecase.usecase.UpdateAppSettingsUseCase
import dev.stupak.worker.AlertsWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var getAppSettingsUseCase: GetAppSettingsUseCase

    @Inject
    lateinit var updateAppSettingsUseCase: UpdateAppSettingsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()

        installSplashScreen()
        firstLaunchStartWorker(applicationContext)
        scheduleAlertsWorker(applicationContext)
        scheduleWidgetWorker(applicationContext)

        setContent {
            val page = intent?.data?.getQueryParameter("page")?.toIntOrNull() ?: 0
            var darkTheme by remember { mutableStateOf(false) }
            val isSystemInDarkTheme = isSystemInDarkTheme()
            LaunchedEffect(Unit) {
                val settings = getAppSettingsUseCase().getOrNull()
                if (settings?.alertsNotifications == true) {
                    val intent = Intent(this@MainActivity, ForegroundNetworkService::class.java)
                    this@MainActivity.startForegroundService(intent)
                }
                darkTheme =
                    when (settings?.theme) {
                        SettingsDomainModel.Theme.LIGHT -> false
                        SettingsDomainModel.Theme.DARK -> true
                        SettingsDomainModel.Theme.AUTO -> isSystemInDarkTheme
                        null -> isSystemInDarkTheme
                    }
            }

            AirAlarmUATheme(useDarkTheme = darkTheme) {
                NavHost(
                    onThemeUpdated = { isDarkTheme -> darkTheme = isDarkTheme },
                    initialPage = page,
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (requestCode == 1001) {
            lifecycleScope.launch {
                getAppSettingsUseCase().getOrNull()?.copy(alertsNotifications = true)?.let {
                    updateAppSettingsUseCase(
                        it,
                    )
                }
            }
            val intent = Intent(applicationContext, ForegroundNetworkService::class.java)
            applicationContext.startForegroundService(intent)
        }
    }

    private fun scheduleAlertsWorker(context: Context) {
        val workRequest =
            OneTimeWorkRequestBuilder<AlertsWorker>()
                .setInitialDelay(15, TimeUnit.SECONDS)
                .build()

        WorkManager.getInstance(context).enqueue(workRequest)
        WorkManager.getInstance(context).getWorkInfoByIdLiveData(workRequest.id).observeForever { info ->
            if (info?.state == WorkInfo.State.SUCCEEDED || info?.state == WorkInfo.State.FAILED) {
                scheduleAlertsWorker(context)
            }
        }
    }

    private fun scheduleWidgetWorker(context: Context) {
        val workRequest =
            OneTimeWorkRequestBuilder<WidgetUpdateWorker>()
                .setInitialDelay(50, TimeUnit.SECONDS)
                .build()

        WorkManager.getInstance(context).enqueue(workRequest)
        WorkManager.getInstance(context).getWorkInfoByIdLiveData(workRequest.id).observeForever { info ->
            if (info?.state == WorkInfo.State.SUCCEEDED || info?.state == WorkInfo.State.FAILED) {
                scheduleWidgetWorker(context)
            }
        }
    }

    private fun firstLaunchStartWorker(context: Context) {
        val workRequest1 =
            OneTimeWorkRequestBuilder<AlertsWorker>()
                .build()
        WorkManager.getInstance(context).enqueue(workRequest1)
        val workRequest2 =
            OneTimeWorkRequestBuilder<WidgetUpdateWorker>()
                .build()
        WorkManager.getInstance(context).enqueue(workRequest2)
    }
}
