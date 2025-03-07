package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.TgMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TelegramDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<TgMessageEntity>)

    @Query("SELECT * FROM messages")
    fun getAllMessages(): Flow<List<TgMessageEntity>>

    @Query("DELETE FROM messages")
    suspend fun deleteAllMessage()
}
