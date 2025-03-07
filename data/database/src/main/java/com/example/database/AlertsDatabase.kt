package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.AlertsDao
import com.example.database.dao.TelegramDao
import com.example.database.model.AlertEntity
import com.example.database.model.TgMessageEntity

@Database(entities = [AlertEntity::class, TgMessageEntity::class], version = 1, exportSchema = false)
abstract class AlertsDatabase : RoomDatabase() {
    abstract fun alertsDao(): AlertsDao
    abstract fun telegramDao(): TelegramDao
}
