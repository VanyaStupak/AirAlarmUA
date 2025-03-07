package dev.stupak.domain.model

import dev.stupak.repository.model.RepositoryAlertsList
import dev.stupak.repository.model.SettingsRepositoryModel


fun RepositoryAlertsList.toDomainModel(): DomainAlertsList {
    return DomainAlertsList(
        alerts = this.alerts.map { apiAlert ->
            AlertsDomainModel(
                id = apiAlert.id,
                locationTitle = apiAlert.locationTitle,
                locationType = apiAlert.locationType,
                startedAt = apiAlert.startedAt,
                finishedAt = apiAlert.finishedAt,
                updatedAt = apiAlert.updatedAt,
                alertType = apiAlert.alertType,
                locationUid = apiAlert.locationUid,
                locationOblast = apiAlert.locationOblast,
                locationOblastUid = apiAlert.locationOblastUid,
                locationRaion = apiAlert.locationRaion,
                notes = apiAlert.notes,
                calculated = apiAlert.calculated,
                country = apiAlert.country
            )
        }
    )
}

fun SettingsRepositoryModel.toDomainModel(): SettingsDomainModel{
    return SettingsDomainModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme = when (theme) {
            SettingsRepositoryModel.Theme.AUTO -> SettingsDomainModel.Theme.AUTO
            SettingsRepositoryModel.Theme.LIGHT -> SettingsDomainModel.Theme.LIGHT
            SettingsRepositoryModel.Theme.DARK -> SettingsDomainModel.Theme.DARK
        },
    )
}

fun SettingsDomainModel.toRepositoryModel(): SettingsRepositoryModel{
    return SettingsRepositoryModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme = when (theme) {
            SettingsDomainModel.Theme.AUTO -> SettingsRepositoryModel.Theme.AUTO
            SettingsDomainModel.Theme.LIGHT -> SettingsRepositoryModel.Theme.LIGHT
            SettingsDomainModel.Theme.DARK -> SettingsRepositoryModel.Theme.DARK
        },
    )
}