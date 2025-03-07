package com.example.settings

data class SettingsState(
    val notifications: Boolean = false,
    val alertsNotifications: Boolean = false,
    val telegramNotifications: Boolean = false,
    val region: String = "",
    val theme: Theme = Theme.AUTO,
){
    enum class Theme{
        LIGHT, DARK, AUTO
    }
}