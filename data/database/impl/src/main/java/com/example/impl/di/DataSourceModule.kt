package com.example.impl.di

import com.example.database.source.datasource.AlertsDatabaseDataSource
import com.example.database.source.datasource.TelegramDatabaseDataSource
import com.example.impl.source.AlertsDatabaseDataSourceImpl
import com.example.impl.source.TelegramDatabaseDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindAlertsDataSource(impl: AlertsDatabaseDataSourceImpl): AlertsDatabaseDataSource

    @Singleton
    @Binds
    abstract fun bindTelegramDataSource(impl: TelegramDatabaseDataSourceImpl): TelegramDatabaseDataSource
}