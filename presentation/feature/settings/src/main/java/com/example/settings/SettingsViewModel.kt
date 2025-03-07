package com.example.settings

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.settings.model.toDomainModel
import com.example.settings.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.stupak.domain.usecase.GetAppSettingsUseCase
import dev.stupak.domain.usecase.UpdateAppSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getAppSettingsUseCase: GetAppSettingsUseCase,
    private val updateAppSettingsUseCase: UpdateAppSettingsUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _settingsState = MutableStateFlow(SettingsState())
    val settingsState: StateFlow<SettingsState> = _settingsState

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            val result = getAppSettingsUseCase.invoke()
            _settingsState.value = when {
                result.isSuccess -> {
                    val settings = result.getOrNull()?.toUiModel()
                    _settingsState.value.copy(
                        notifications = settings?.notifications ?: false,
                        alertsNotifications = settings?.alertsNotifications ?: false,
                        telegramNotifications = settings?.telegramNotifications ?: false,
                        theme = settings?.theme ?: SettingsState.Theme.AUTO,
                        region = settings?.region ?: "",
                    )
                }
                else -> _settingsState.value
            }
        }
    }

    fun onAction(action: SettingsIntent) {
        when (action) {
            is SettingsIntent.ToggleNotifications -> {
                updateSetting { copy(notifications = !_settingsState.value.notifications) }
            }
            is SettingsIntent.ToggleAlertsNotifications -> {
                val newAlertsNotificationsState = !_settingsState.value.alertsNotifications
                updateSetting { copy(alertsNotifications = newAlertsNotificationsState) }

                if (newAlertsNotificationsState) {
                    startForegroundService()
                } else {
                    stopForegroundService()
                }
            }
            is SettingsIntent.ToggleTelegramNotifications -> {
                updateSetting { copy(telegramNotifications = !_settingsState.value.telegramNotifications) }
            }
            is SettingsIntent.ChangeTheme -> {
                updateSetting { copy(theme = action.theme) }
            }
            is SettingsIntent.ChangeRegion -> {
                updateSetting { copy(region = action.region) }
            }
        }
    }

    private fun updateSetting(update: SettingsState.() -> SettingsState) {
        viewModelScope.launch {
            try {
                val updatedSettings = _settingsState.value.update()

                updateAppSettingsUseCase.invoke(updatedSettings.toDomainModel())

                _settingsState.value = updatedSettings
            } catch (e: Exception) {
                 Log.e("SettingsViewModel", "Error updating settings", e)
            }
        }
    }
    private fun startForegroundService() {
        val intent = Intent(context, ForegroundNetworkService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }

    private fun stopForegroundService() {
        val intent = Intent(context, ForegroundNetworkService::class.java)
        context.stopService(intent)
    }

}
