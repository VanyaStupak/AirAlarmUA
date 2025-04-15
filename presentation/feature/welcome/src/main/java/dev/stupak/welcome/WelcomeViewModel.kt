package dev.stupak.welcome

import android.content.Context
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.stupak.platform.viewmodel.BaseViewModel
import dev.stupak.usecase.usecase.GetAppSettingsUseCase
import dev.stupak.usecase.usecase.UpdateAppSettingsUseCase
import dev.stupak.welcome.model.toDomainModel
import dev.stupak.welcome.model.toWelcomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel
    @Inject
    constructor(
        private val getAppSettingsUseCase: GetAppSettingsUseCase,
        private val updateAppSettingsUseCase: UpdateAppSettingsUseCase,
        @ApplicationContext context: Context,
    ) : BaseViewModel() {
        private val _welcomeState = MutableStateFlow(WelcomeState())
        val welcomeState: StateFlow<WelcomeState> = _welcomeState

        private val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

        init {
            loadSettings()
        }

        private fun loadSettings() {
            launch {
                val result = getAppSettingsUseCase.invoke()
                _welcomeState.value =
                    when {
                        result.isSuccess -> {
                            val settings = result.getOrNull()?.toWelcomeState()
                            _welcomeState.value.copy(
                                region = settings?.region ?: "",
                                isFirstRun = sharedPreferences.getBoolean("is_first_run", true),
                                notifications = settings?.notifications ?: false,
                                alertsNotifications = settings?.alertsNotifications ?: false,
                                telegramNotifications = settings?.telegramNotifications ?: false,
                                theme = settings?.theme ?: WelcomeState.Theme.AUTO,
                            )
                        }
                        else -> _welcomeState.value
                    }
            }
        }

        fun onAction(action: WelcomeIntent) {
            when (action) {
                is WelcomeIntent.SetRegion -> {
                    updateSetting { copy(region = action.region) }
                }
            }
        }

        private fun updateSetting(update: WelcomeState.() -> WelcomeState) {
            launch {
                try {
                    val updatedSettings = _welcomeState.value.update()
                    updateAppSettingsUseCase.invoke(updatedSettings.toDomainModel())
                    _welcomeState.value = updatedSettings
                } catch (e: Exception) {
                    Log.e("SettingsViewModel", "Error updating settings", e)
                }
            }
        }
    }
