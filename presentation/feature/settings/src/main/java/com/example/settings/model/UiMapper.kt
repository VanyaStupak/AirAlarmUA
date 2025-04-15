package com.example.settings.model

import com.example.settings.SettingsState
import dev.stupak.usecase.model.SettingsDomainModel

fun SettingsDomainModel.toUiModel(): SettingsState =
    SettingsState(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                SettingsDomainModel.Theme.AUTO -> SettingsState.Theme.AUTO
                SettingsDomainModel.Theme.LIGHT -> SettingsState.Theme.LIGHT
                SettingsDomainModel.Theme.DARK -> SettingsState.Theme.DARK
            },
    )

fun SettingsState.toDomainModel(): SettingsDomainModel =
    SettingsDomainModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                SettingsState.Theme.AUTO -> SettingsDomainModel.Theme.AUTO
                SettingsState.Theme.LIGHT -> SettingsDomainModel.Theme.LIGHT
                SettingsState.Theme.DARK -> SettingsDomainModel.Theme.DARK
            },
    )
