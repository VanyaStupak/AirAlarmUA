package dev.stupak.usecase.impl.mapper

import dev.stupak.repository.model.AlertsRepositoryModel
import dev.stupak.repository.model.RepositoryAlertsList
import dev.stupak.repository.model.SettingsRepositoryModel
import dev.stupak.usecase.model.AlertsDomainModel
import dev.stupak.usecase.model.DomainAlertsList
import dev.stupak.usecase.model.SettingsDomainModel

fun RepositoryAlertsList.toDomainModel(): DomainAlertsList =
    DomainAlertsList(
        alerts =
            this.alerts.map { apiAlert ->
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
                    country = apiAlert.country,
                )
            },
    )

fun DomainAlertsList.toRepositoryAlertsList(): RepositoryAlertsList =
    RepositoryAlertsList(
        alerts =
            alerts.map { alert ->
                AlertsRepositoryModel(
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
            },
    )

fun SettingsRepositoryModel.toDomainModel(): SettingsDomainModel =
    SettingsDomainModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                SettingsRepositoryModel.Theme.AUTO -> SettingsDomainModel.Theme.AUTO
                SettingsRepositoryModel.Theme.LIGHT -> SettingsDomainModel.Theme.LIGHT
                SettingsRepositoryModel.Theme.DARK -> SettingsDomainModel.Theme.DARK
            },
    )

fun SettingsDomainModel.toRepositoryModel(): SettingsRepositoryModel =
    SettingsRepositoryModel(
        notifications = notifications,
        alertsNotifications = alertsNotifications,
        telegramNotifications = telegramNotifications,
        region = region,
        theme =
            when (theme) {
                SettingsDomainModel.Theme.AUTO -> SettingsRepositoryModel.Theme.AUTO
                SettingsDomainModel.Theme.LIGHT -> SettingsRepositoryModel.Theme.LIGHT
                SettingsDomainModel.Theme.DARK -> SettingsRepositoryModel.Theme.DARK
            },
    )
