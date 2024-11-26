package dev.stupak.main.model

data class AlertsUiModel(
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

data class UiAlertsList(
    val alerts: List<AlertsUiModel>
)