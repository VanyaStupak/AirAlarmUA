package dev.stupak.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Alert(
    val id: Int,
    @SerialName("location_title") val locationTitle: String,
    @SerialName("location_type") val locationType: String,
    @SerialName("started_at") val startedAt: String,
    @SerialName("finished_at") val finishedAt: String?,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("alert_type") val alertType: String,
    @SerialName("location_uid") val locationUid: String,
    @SerialName("location_oblast") val locationOblast: String,
    @SerialName("location_oblast_uid") val locationOblastUid: Int,
    @SerialName("location_raion") val locationRaion: String? = null,
    val notes: String?,
    val calculated: Boolean?,
    val country: String?
)

@Serializable
data class ActiveAlertsModel(
    val alerts: List<Alert>
)