package dev.stupak.welcome.model

import dev.stupak.usecase.model.SettingsDomainModel
import dev.stupak.welcome.WelcomeState

fun SettingsDomainModel.toUiModel(): WelcomeUiModel =
    WelcomeUiModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                SettingsDomainModel.Theme.AUTO -> WelcomeUiModel.Theme.AUTO
                SettingsDomainModel.Theme.LIGHT -> WelcomeUiModel.Theme.LIGHT
                SettingsDomainModel.Theme.DARK -> WelcomeUiModel.Theme.DARK
            },
    )

fun WelcomeUiModel.toDomainModel(): SettingsDomainModel =
    SettingsDomainModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                WelcomeUiModel.Theme.AUTO -> dev.stupak.usecase.model.SettingsDomainModel.Theme.AUTO
                WelcomeUiModel.Theme.LIGHT -> dev.stupak.usecase.model.SettingsDomainModel.Theme.LIGHT
                WelcomeUiModel.Theme.DARK -> dev.stupak.usecase.model.SettingsDomainModel.Theme.DARK
            },
    )

fun WelcomeUiModel.toWelcomeState(): WelcomeState =
    WelcomeState(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                WelcomeUiModel.Theme.AUTO -> WelcomeState.Theme.AUTO
                WelcomeUiModel.Theme.LIGHT -> WelcomeState.Theme.LIGHT
                WelcomeUiModel.Theme.DARK -> WelcomeState.Theme.DARK
            },
    )

fun WelcomeState.toDomainModel(): SettingsDomainModel =
    SettingsDomainModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                WelcomeState.Theme.AUTO -> dev.stupak.usecase.model.SettingsDomainModel.Theme.AUTO
                WelcomeState.Theme.LIGHT -> dev.stupak.usecase.model.SettingsDomainModel.Theme.LIGHT
                WelcomeState.Theme.DARK -> dev.stupak.usecase.model.SettingsDomainModel.Theme.DARK
            },
    )
