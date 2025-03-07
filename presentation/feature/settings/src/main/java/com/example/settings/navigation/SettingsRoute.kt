package com.example.settings.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.settings.SettingsScreen
import com.example.settings.SettingsViewModel

@SuppressLint("NewApi")
@Composable
internal fun SettingsRoute(
    onBackClick: () -> Unit,
    onChangeRegionClick: () -> Unit,
    onThemeUpdated: (Boolean) -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {

    val uiState by viewModel.settingsState.collectAsStateWithLifecycle()

    SettingsScreen(
        onBackClick = onBackClick,
        onChangeRegionClick = onChangeRegionClick,
        onThemeUpdated = onThemeUpdated,
        onAction = viewModel::onAction,
        region = uiState.region,
        alertNotifications = uiState.alertsNotifications,
        telegramNotifications = uiState.telegramNotifications,
        theme = uiState.theme,
    )

}