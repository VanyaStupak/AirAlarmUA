package dev.stupak.network.service

interface TelegramService {

    suspend fun getTgAlerts(): List<String>

}