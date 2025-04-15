package dev.stupak.database.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.stupak.database.core.model.AlertDatabaseModel
import dev.stupak.database.core.model.TelegramDatabaseModel
import dev.stupak.database.impl.source.dao.AlertsDao
import dev.stupak.database.impl.source.dao.TelegramDao

@Database(entities = [AlertDatabaseModel::class, TelegramDatabaseModel::class], version = 1, exportSchema = false)
abstract class AlertsDatabase : RoomDatabase() {
    abstract fun alertsDao(): AlertsDao

    abstract fun telegramDao(): TelegramDao
}
