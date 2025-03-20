package com.example.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.impl.source.dao.AlertsDao
import com.example.database.core.model.AlertDatabaseModel
import com.example.database.core.model.TelegramDatabaseModel
import com.example.impl.source.dao.TelegramDao

@Database(entities = [AlertDatabaseModel::class, TelegramDatabaseModel::class], version = 1, exportSchema = false)
abstract class AlertsDatabase : RoomDatabase() {
    abstract fun alertsDao(): AlertsDao
    abstract fun telegramDao(): TelegramDao
}
