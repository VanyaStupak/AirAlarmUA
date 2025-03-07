package dev.stupak.welcome.model

import dev.stupak.domain.model.SettingsDomainModel
import dev.stupak.welcome.WelcomeState



fun WelcomeState.toDomainModel(): SettingsDomainModel{
    return SettingsDomainModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme = when (theme) {
            WelcomeState.Theme.AUTO -> SettingsDomainModel.Theme.AUTO
            WelcomeState.Theme.LIGHT -> SettingsDomainModel.Theme.LIGHT
            WelcomeState.Theme.DARK -> SettingsDomainModel.Theme.DARK
        }
    )

}

fun SettingsDomainModel.toWelcomeState(): WelcomeState {
    return WelcomeState(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme = when (theme) {
            SettingsDomainModel.Theme.AUTO -> WelcomeState.Theme.AUTO
            SettingsDomainModel.Theme.LIGHT -> WelcomeState.Theme.LIGHT
            SettingsDomainModel.Theme.DARK -> WelcomeState.Theme.DARK
        },
    )
}