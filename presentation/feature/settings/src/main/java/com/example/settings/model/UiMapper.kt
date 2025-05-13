package com.example.settings.model

import com.example.settings.SettingsState
import dev.stupak.usecase.model.SettingsDomainModel

fun SettingsDomainModel.toUiModel(): UiModel =
    UiModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                SettingsDomainModel.Theme.AUTO -> UiModel.Theme.AUTO
                SettingsDomainModel.Theme.LIGHT -> UiModel.Theme.LIGHT
                SettingsDomainModel.Theme.DARK -> UiModel.Theme.DARK
            },
    )

fun UiModel.toDomainModel(): SettingsDomainModel =
    SettingsDomainModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                UiModel.Theme.AUTO -> SettingsDomainModel.Theme.AUTO
                UiModel.Theme.LIGHT -> SettingsDomainModel.Theme.LIGHT
                UiModel.Theme.DARK -> SettingsDomainModel.Theme.DARK
            },
    )

fun UiModel.toSettingsState(): SettingsState =
    SettingsState(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                UiModel.Theme.AUTO -> SettingsState.Theme.AUTO
                UiModel.Theme.LIGHT -> SettingsState.Theme.LIGHT
                UiModel.Theme.DARK -> SettingsState.Theme.DARK
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
