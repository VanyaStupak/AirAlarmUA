package dev.stupak.repository.model

import com.example.database.model.AlertEntity
import com.example.database.model.TgMessageEntity
import com.example.local.model.SettingsDataStoreModel
import dev.stupak.network.model.AlertsList


fun AlertsList.toRepositoryModel(): RepositoryAlertsList {
    return RepositoryAlertsList(
        alerts = this.alerts.map { apiAlert ->
            AlertsRepositoryModel(
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


fun TgMessageEntity.toTelegramRepositoryModel(): TelegramRepositoryModel {
    return TelegramRepositoryModel(
        message = this.message
    )
}

fun List<AlertEntity>.toRepositoryList(): RepositoryAlertsList {
    return RepositoryAlertsList(
        alerts = this.map { entity ->
            AlertsRepositoryModel(
                id = entity.id,
                locationTitle = entity.locationTitle,
                locationType = entity.locationType,
                startedAt = entity.startedAt,
                finishedAt = entity.finishedAt,
                updatedAt = entity.updatedAt,
                alertType = entity.alertType,
                locationUid = entity.locationUid,
                locationOblast = entity.locationOblast,
                locationOblastUid = entity.locationOblastUid,
                locationRaion = entity.locationRaion,
                notes = entity.notes,
                calculated = entity.calculated,
                country = entity.country
            )
        }
    )
}


fun SettingsDataStoreModel.toRepositoryModel(): SettingsRepositoryModel{
    return SettingsRepositoryModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme = when (theme) {
            SettingsDataStoreModel.Theme.AUTO -> SettingsRepositoryModel.Theme.AUTO
            SettingsDataStoreModel.Theme.LIGHT -> SettingsRepositoryModel.Theme.LIGHT
            SettingsDataStoreModel.Theme.DARK -> SettingsRepositoryModel.Theme.DARK
        },
    )
}

fun SettingsRepositoryModel.toDataStoreModel(): SettingsDataStoreModel{
    return SettingsDataStoreModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme = when (theme) {
            SettingsRepositoryModel.Theme.AUTO -> SettingsDataStoreModel.Theme.AUTO
            SettingsRepositoryModel.Theme.LIGHT -> SettingsDataStoreModel.Theme.LIGHT
            SettingsRepositoryModel.Theme.DARK -> SettingsDataStoreModel.Theme.DARK
        },
    )
}