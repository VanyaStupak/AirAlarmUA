package com.example.impl.di

import android.content.Context
import androidx.room.Room
import com.example.database.source.datasource.AlertsDatabaseDataSource
import com.example.impl.AlertsDatabase
import com.example.impl.source.dao.AlertsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
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