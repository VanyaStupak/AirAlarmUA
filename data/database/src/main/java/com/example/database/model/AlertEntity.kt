package com.example.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alerts")
data class AlertEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "location_title") val locationTitle: String,
    @ColumnInfo(name = "location_type") val locationType: String,
    @ColumnInfo(name = "started_at") val startedAt: String,
    @ColumnInfo(name = "finished_at") val finishedAt: String?,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
    @ColumnInfo(name = "alert_type") val alertType: String,
    @ColumnInfo(name = "location_uid") val locationUid: String,
    @ColumnInfo(name = "location_oblast") val locationOblast: String,
    @ColumnInfo(name = "location_oblast_uid") val locationOblastUid: Int,
    @ColumnInfo(name = "location_raion") val locationRaion: String? = null,
    @ColumnInfo(name = "notes") val notes: String?,
    @ColumnInfo(name = "calculated") val calculated: Boolean?,
    @ColumnInfo(name = "country") val country: String?
)