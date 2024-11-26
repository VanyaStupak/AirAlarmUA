package dev.stupak.repository.model

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