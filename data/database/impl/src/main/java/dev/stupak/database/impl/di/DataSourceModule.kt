package dev.stupak.database.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.stupak.database.impl.source.AlertsDatabaseDataSourceImpl
import dev.stupak.database.impl.source.TelegramDatabaseDataSourceImpl
import dev.stupak.database.source.datasource.AlertsDatabaseDataSource
import dev.stupak.database.source.datasource.TelegramDatabaseDataSource
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
