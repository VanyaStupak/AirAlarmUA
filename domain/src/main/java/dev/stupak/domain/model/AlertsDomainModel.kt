package dev.stupak.domain.model

data class AlertsDomainModel(
    val id: Int,
    val locationTitle: String,
    val locationType: String,
    val startedAt: String,
    val finishedAt: String?,
    val updatedAt: String,
    val alertType: String,
    val locationUid: String,
    val locationOblast: String,
    val locationOblastUid: Int,
    val locationRaion: String? = null,
    val notes: String?,
    val calculated: Boolean?,
    val country: String?
)

data class DomainAlertsList(
    val alerts: List<AlertsDomainModel>
)