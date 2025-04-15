package dev.stupak.local.model

import com.example.local.AppSettings

fun AppSettings.toSettingsDataStoreModel(): SettingsDataStoreModel =
    SettingsDataStoreModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        theme =
            when (theme) {
                AppSettings.Theme.AUTO -> SettingsDataStoreModel.Theme.AUTO
                AppSettings.Theme.LIGHT -> SettingsDataStoreModel.Theme.LIGHT
                AppSettings.Theme.DARK -> SettingsDataStoreModel.Theme.DARK
                else -> SettingsDataStoreModel.Theme.AUTO
            },
        region = region,
    )
