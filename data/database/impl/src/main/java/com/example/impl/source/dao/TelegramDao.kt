package com.example.impl.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.core.model.TelegramDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TelegramDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<TelegramDatabaseModel>)

    @Query("SELECT * FROM messages")
    fun getAllMessages(): Flow<List<TelegramDatabaseModel>>

    @Query("DELETE FROM messages")
    suspend fun deleteAllMessage()
}