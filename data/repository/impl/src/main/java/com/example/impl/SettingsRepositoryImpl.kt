package com.example.impl

import com.example.local.settings.AppSettingsDataStore
import dev.stupak.repository.SettingsRepository
import dev.stupak.repository.model.SettingsRepositoryModel
import dev.stupak.repository.model.toDataStoreModel
import dev.stupak.repository.model.toRepositoryModel
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val appSettingsDataStore: AppSettingsDataStore
) : SettingsRepository {
    override suspend fun getSettings(): SettingsRepositoryModel {
        return appSettingsDataStore.appSettings.firstOrNull()?.toRepositoryModel() ?: SettingsRepositoryModel(
            notifications = false,
            alertsNotifications = false,
            telegramNotifications = false,
            region = "Київська область",
            theme = SettingsRepositoryModel.Theme.AUTO
        )
    }

    override suspend fun updateSettings(settings:  SettingsRepositoryModel) {
       appSettingsDataStore.updateSettings(settings.toDataStoreModel())
    }
}
