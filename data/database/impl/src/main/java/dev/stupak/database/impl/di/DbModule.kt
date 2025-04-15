package dev.stupak.database.impl.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.stupak.database.impl.AlertsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext context: Context,
    ) = Room
        .databaseBuilder(
            context,
            AlertsDatabase::class.java,
            "alerts.db",
        ).build()

    @Provides
    @Singleton
    fun provideAlertsDao(db: AlertsDatabase) = db.alertsDao()

    @Provides
    @Singleton
    fun provideTelegramDao(db: AlertsDatabase) = db.telegramDao()
}
