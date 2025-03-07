package dev.stupak.worker.mapper

import com.example.database.model.AlertEntity
import com.example.database.model.TgMessageEntity
import dev.stupak.network.model.AlertsList

fun AlertsList.toAlertsEntityList(): List<AlertEntity> {
    return alerts.map { alert ->
        AlertEntity(
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
            country = alert.country
        )
    }
}
fun String.toTgMessageEntity(): TgMessageEntity {
    return TgMessageEntity(message = this)
}

fun List<String>.toTgMessageEntityList(): List<TgMessageEntity> {
    return this.map { it.toTgMessageEntity() }
}