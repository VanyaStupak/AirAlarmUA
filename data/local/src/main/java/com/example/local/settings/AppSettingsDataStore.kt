package com.example.local.settings

import com.example.local.model.SettingsDataStoreModel
import kotlinx.coroutines.flow.Flow

interface AppSettingsDataStore {
    val appSettings: Flow<SettingsDataStoreModel>
    suspend fun updateSettings(settings: SettingsDataStoreModel)
}