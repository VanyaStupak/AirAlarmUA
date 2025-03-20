package dev.stupak.network.source.datasource.telegram

interface TelegramNetSource {

    suspend fun getTgAlerts(): List<String>

}