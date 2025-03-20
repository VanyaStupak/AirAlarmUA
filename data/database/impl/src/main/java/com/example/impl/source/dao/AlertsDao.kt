package com.example.impl.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.core.model.AlertDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AlertsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlerts(alerts: List<AlertDatabaseModel>)

    @Query("SELECT * FROM alerts")
    fun getAllAlerts(): Flow<List<AlertDatabaseModel>>

    @Query("DELETE FROM alerts WHERE location_title NOT IN (:titles)")
    suspend fun deleteAlertsNotIn(titles: List<String>)
}