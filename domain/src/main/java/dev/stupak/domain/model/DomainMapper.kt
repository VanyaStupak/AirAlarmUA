package dev.stupak.domain.model

import dev.stupak.repository.model.RepositoryAlertsList

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