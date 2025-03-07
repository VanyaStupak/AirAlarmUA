package com.example.settings.model

import com.example.settings.SettingsState
import dev.stupak.domain.model.SettingsDomainModel

fun SettingsDomainModel.toUiModel(): SettingsState {
    return SettingsState(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme = when (theme) {
            SettingsDomainModel.Theme.AUTO -> SettingsState.Theme.AUTO
            SettingsDomainModel.Theme.LIGHT -> SettingsState.Theme.LIGHT
            SettingsDomainModel.Theme.DARK -> SettingsState.Theme.DARK
        },
    )
}

fun SettingsState.toDomainModel(): SettingsDomainModel {
    return SettingsDomainModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme = when (theme) {
            SettingsState.Theme.AUTO -> SettingsDomainModel.Theme.AUTO
            SettingsState.Theme.LIGHT -> SettingsDomainModel.Theme.LIGHT
            SettingsState.Theme.DARK -> SettingsDomainModel.Theme.DARK
        },
    )
}