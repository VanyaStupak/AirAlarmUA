package dev.stupak.local.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import com.example.local.AppSettings
import dev.stupak.local.model.SettingsDataStoreModel
import dev.stupak.local.model.toSettingsDataStoreModel
import dev.stupak.local.settings.AppSettingsDataStore
import dev.stupak.local.settings.SettingsSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val Context.appSettingsDataStore: DataStore<AppSettings> by dataStore(
    fileName = "settings_data",
    serializer = SettingsSerializer(),
)

class AppSettingsDataStoreImpl(
    context: Context,
) : AppSettingsDataStore {
    private val dataStore = context.appSettingsDataStore
    override val appSettings: Flow<SettingsDataStoreModel>
        get() =
            dataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(AppSettings.getDefaultInstance())
                    } else {
                        throw exception
                    }
                }.map {
                    it.toSettingsDataStoreModel()
                }

    override suspend fun updateSettings(settings: SettingsDataStoreModel) {
        dataStore.updateData { preference ->
            preference
                .toBuilder()
                .setNotifications(settings.notifications)
                .setAlertsNotifications(settings.alertsNotifications)
                .setTelegramNotifications(settings.telegramNotifications)
                .setThemeValue(settings.theme.ordinal)
                .setRegion(settings.region)
                .build()
        }
    }
}
