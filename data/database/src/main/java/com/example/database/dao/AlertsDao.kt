package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.AlertEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlertsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlerts(alerts: List<AlertEntity>)

    @Query("SELECT * FROM alerts")
    fun getAllAlerts(): Flow<List<AlertEntity>>

    @Query("DELETE FROM alerts WHERE location_title NOT IN (:titles)")
    suspend fun deleteAlertsNotIn(titles: List<String>)
}