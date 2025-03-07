package dev.stupak.repository

import dev.stupak.repository.model.SettingsRepositoryModel

interface SettingsRepository {
    suspend fun getSettings(): SettingsRepositoryModel
    suspend fun updateSettings(settings:  SettingsRepositoryModel)
}