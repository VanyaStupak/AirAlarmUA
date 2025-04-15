package dev.stupak.local.settings

import dev.stupak.local.model.SettingsDataStoreModel
import kotlinx.coroutines.flow.Flow

interface AppSettingsDataStore {
    val appSettings: Flow<SettingsDataStoreModel>

    suspend fun updateSettings(settings: SettingsDataStoreModel)
}
