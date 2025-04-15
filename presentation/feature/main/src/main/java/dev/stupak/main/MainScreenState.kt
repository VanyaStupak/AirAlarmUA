package dev.stupak.main

import dev.stupak.main.model.AlertsUiModel

data class MainScreenState(
    val alertsList: List<AlertsUiModel> = emptyList(),
    val alertsHistoryList: List<AlertsUiModel> = emptyList(),
    val filteredLocations: Set<String> = emptySet(),
    val region: String = "",
    val oblastAlert: Boolean = false,
    val isFirstRun: Boolean = true,
    val isLoading: Boolean = false,
    val isHistoryLoading: Boolean = false,
    val error: String? = null,
    val historyError: String? = null,
    val isConnected: Boolean = true,
)
