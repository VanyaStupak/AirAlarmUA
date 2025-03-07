package com.example.settings

sealed class SettingsIntent {
    data object ToggleNotifications : SettingsIntent()
    data object ToggleAlertsNotifications : SettingsIntent()
    data object ToggleTelegramNotifications : SettingsIntent()
    data class ChangeTheme(val theme: SettingsState.Theme) : SettingsIntent()
    data class ChangeRegion(val region: String) : SettingsIntent()
}