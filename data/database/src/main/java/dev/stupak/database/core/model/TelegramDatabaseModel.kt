package dev.stupak.database.core.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.stupak.database.core.model.base.BaseDatabaseModel

@Entity(tableName = "messages")
data class TelegramDatabaseModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "message") val message: String,
) : BaseDatabaseModel
