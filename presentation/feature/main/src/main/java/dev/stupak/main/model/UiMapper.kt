package dev.stupak.main.model

import dev.stupak.usecase.model.DomainAlertsList

fun DomainAlertsList.toUiModel(): UiAlertsList =
    UiAlertsList(
        alerts =
            this.alerts.map { apiAlert ->
                AlertsUiModel(
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
