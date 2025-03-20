package dev.stupak.repository

import dev.stupak.repository.model.TelegramRepositoryModel
import kotlinx.coroutines.flow.Flow

interface TelegramRepository {

    suspend fun getTgAlerts(): Flow<List<TelegramRepositoryModel>>

    suspend fun getTgAlertsFromNet(): List<TelegramRepositoryModel>

    suspend fun insertTelegramMessages(messages: List<TelegramRepositoryModel>)

    suspend fun deleteTelegramMessages()
}