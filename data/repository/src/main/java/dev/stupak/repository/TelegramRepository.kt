package dev.stupak.repository

interface TelegramRepository {

    suspend fun getTgAlerts(): List<String>

}