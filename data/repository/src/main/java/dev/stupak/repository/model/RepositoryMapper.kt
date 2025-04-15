package dev.stupak.repository.model

import dev.stupak.database.core.model.AlertDatabaseModel
import dev.stupak.database.core.model.TelegramDatabaseModel
import dev.stupak.local.model.SettingsDataStoreModel
import dev.stupak.network.model.AlertsList

fun AlertsList.toRepositoryModel(): RepositoryAlertsList =
    RepositoryAlertsList(
        alerts =
            this.alerts.map { apiAlert ->
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
                    country = apiAlert.country,
                )
            },
    )

fun String.toTgRepositoryModel(): TelegramRepositoryModel = TelegramRepositoryModel(message = this)

fun List<String>.toTelegramRepositoryModel(): List<TelegramRepositoryModel> = this.map { it.toTgRepositoryModel() }

fun RepositoryAlertsList.toAlertEntityList(): List<AlertDatabaseModel> =
    alerts.map { alert ->
        AlertDatabaseModel(
            id = alert.id,
            locationTitle = alert.locationTitle,
            locationType = alert.locationType,
            startedAt = alert.startedAt,
            finishedAt = alert.finishedAt,
            updatedAt = alert.updatedAt,
            alertType = alert.alertType,
            locationUid = alert.locationUid,
            locationOblast = alert.locationOblast,
            locationOblastUid = alert.locationOblastUid,
            locationRaion = alert.locationRaion,
            notes = alert.notes,
            calculated = alert.calculated,
            country = alert.country,
        )
    }

fun TelegramDatabaseModel.toTelegramRepositoryModel(): TelegramRepositoryModel =
    TelegramRepositoryModel(
        message = this.message,
    )

fun List<TelegramRepositoryModel>.toTelegramDatabaseModelList(): List<TelegramDatabaseModel> = map { it.toTelegramDatabaseModel() }

fun TelegramRepositoryModel.toTelegramDatabaseModel(): TelegramDatabaseModel =
    TelegramDatabaseModel(
        message = this.message,
    )

fun List<AlertDatabaseModel>.toRepositoryList(): RepositoryAlertsList =
    RepositoryAlertsList(
        alerts =
            this.map { entity ->
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
                    country = entity.country,
                )
            },
    )

fun SettingsDataStoreModel.toRepositoryModel(): SettingsRepositoryModel =
    SettingsRepositoryModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                SettingsDataStoreModel.Theme.AUTO -> SettingsRepositoryModel.Theme.AUTO
                SettingsDataStoreModel.Theme.LIGHT -> SettingsRepositoryModel.Theme.LIGHT
                SettingsDataStoreModel.Theme.DARK -> SettingsRepositoryModel.Theme.DARK
            },
    )

fun SettingsRepositoryModel.toDataStoreModel(): SettingsDataStoreModel =
    SettingsDataStoreModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                SettingsRepositoryModel.Theme.AUTO -> SettingsDataStoreModel.Theme.AUTO
                SettingsRepositoryModel.Theme.LIGHT -> SettingsDataStoreModel.Theme.LIGHT
                SettingsRepositoryModel.Theme.DARK -> SettingsDataStoreModel.Theme.DARK
            },
    )
