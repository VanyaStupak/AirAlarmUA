package dev.stupak.main

import android.content.Context
import android.net.NetworkCapabilities
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.stupak.main.common.getRegionUid
import dev.stupak.main.model.toUiModel
import dev.stupak.platform.ConnectivityObserver
import dev.stupak.platform.viewmodel.BaseViewModel
import dev.stupak.usecase.model.DomainAlertsList
import dev.stupak.usecase.usecase.GetActiveAlertsInfoUseCase
import dev.stupak.usecase.usecase.GetAlertsHistoryUseCase
import dev.stupak.usecase.usecase.GetAppSettingsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val getActiveAlertsUseCase: GetActiveAlertsInfoUseCase,
        private val getAppSettingsUseCase: GetAppSettingsUseCase,
        private val getAlertsHistoryUseCase: GetAlertsHistoryUseCase,
        @ApplicationContext private val context: Context,
    ) : BaseViewModel() {
        private val _mainUiState = MutableStateFlow(MainScreenState())
        val mainUiState: StateFlow<MainScreenState> = _mainUiState

        private val connectivityObserver = ConnectivityObserver(context)

        private var isFirstLaunch = true
        private var wasHistoryLoaded = false
        private var wasOfflineBefore = false
        private val sharedPreferences =
            context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

        init {
            observeConnectivity()
            _mainUiState.value =
                _mainUiState.value.copy(
                    isFirstRun = sharedPreferences.getBoolean("is_first_run", true),
                )

            if (sharedPreferences.getBoolean("is_first_run", true)) {
                sharedPreferences.edit().putBoolean("is_first_run", false).apply()
            }
            fetchAlerts(context)
            launch {
                delay(1000)
                val appSettings = getAppSettingsUseCase()
                appSettings.getOrNull()?.region?.let { region ->
                    loadHistory(region)
                }
            }
        }

        private fun observeConnectivity() {
            launch {
                val initialConnected = isNetworkConnected()
                _mainUiState.value = _mainUiState.value.copy(isConnected = initialConnected)

                connectivityObserver.isConnected.collect { connected ->
                    _mainUiState.value = _mainUiState.value.copy(isConnected = connected)
                    if (connected) {
                        if (wasOfflineBefore && !wasHistoryLoaded) {
                            val region = _mainUiState.value.region
                            if (region.isNotEmpty()) {
                                loadHistory(region)
                            }
                        }
                        wasOfflineBefore = false
                    } else {
                        wasOfflineBefore = true
                    }
                }
            }
        }

        private fun loadHistory(regionName: String) {
            _mainUiState.value = _mainUiState.value.copy(isHistoryLoading = true)
            launch {
                val history = getAlertsHistoryUseCase.invoke(getRegionUid(regionName = regionName), "month_ago")
                _mainUiState.value =
                    when {
                        history.isSuccess -> {
                            val alertsHistory = history.getOrNull()?.toUiModel()
                            wasHistoryLoaded = true
                            _mainUiState.value.copy(
                                alertsHistoryList = alertsHistory?.alerts ?: emptyList(),
                                isHistoryLoading = false,
                                historyError = null,
                            )
                        }

                        history.isFailure -> {
                            _mainUiState.value.copy(
                                historyError = context.getString(dev.stupak.ui.R.string.history_error),
                                isHistoryLoading = false,
                            )
                        }

                        else -> _mainUiState.value
                    }
            }
        }

        private fun fetchAlerts(context: Context) {
            launch {
                if (isFirstLaunch) {
                    _mainUiState.value = _mainUiState.value.copy(isLoading = true)
                }
                getActiveAlertsUseCase()
                    .onStart {
                        _mainUiState.value = _mainUiState.value.copy(isLoading = true, error = null)
                    }.collect { result: Result<DomainAlertsList> ->
                        _mainUiState.value =
                            when {
                                result.isSuccess -> {
                                    val alerts = result.getOrNull()?.toUiModel()
                                    val settings = getAppSettingsUseCase.invoke()
                                    val region = settings.getOrNull()?.region
                                    _mainUiState.value.copy(
                                        alertsList = alerts?.alerts ?: emptyList(),
                                        isLoading = false,
                                        error = null,
                                        region = region.toString(),
                                    )
                                }

                                result.isFailure -> {
                                    _mainUiState.value.copy(
                                        isLoading = false,
                                        error = context.getString(dev.stupak.ui.R.string.error),
                                    )
                                }

                                else -> _mainUiState.value
                            }
                        isFirstLaunch = false
                    }
            }
        }

        private fun isNetworkConnected(): Boolean {
            val network = connectivityObserver.connectivityManager.activeNetwork
            val networkCapabilities = connectivityObserver.connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) == true
        }
    }
