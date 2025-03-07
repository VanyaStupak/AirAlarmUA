package dev.stupak.repository.impl

import com.example.local.settings.AppSettingsDataStore
import dev.stupak.repository.SettingsRepository
import dev.stupak.repository.model.SettingsRepositoryModel
import dev.stupak.repository.model.toDataStoreModel
import dev.stupak.repository.model.toRepositoryModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val appSettingsDataStore: AppSettingsDataStore
) : SettingsRepository {
    override suspend fun getSettings(): SettingsRepositoryModel {
        return appSettingsDataStore.appSettings.firstOrNull()?.toRepositoryModel() ?: SettingsRepositoryModel(
            notifications = TODO(),
            alertsNotifications = TODO(),
            telegramNotifications = TODO(),
            region = TODO(),
            theme = TODO()
        )
    }

    override suspend fun updateSettings(settings:  SettingsRepositoryModel) {
       appSettingsDataStore.updateSettings(settings.toDataStoreModel())
    }
}
