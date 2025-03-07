package dev.stupak.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.stupak.repository.AlertsRepository
import dev.stupak.repository.SettingsRepository
import dev.stupak.repository.TelegramRepository
import dev.stupak.repository.impl.AlertsRepositoryImpl
import dev.stupak.repository.impl.SettingsRepositoryImpl
import dev.stupak.repository.impl.TelegramRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindAlertsRepository(impl:AlertsRepositoryImpl ): AlertsRepository

    @Singleton
    @Binds
    abstract fun bindSettingsRepository(impl:SettingsRepositoryImpl ): SettingsRepository

    @Singleton
    @Binds
    abstract fun bindTelegramCRepository(impl: TelegramRepositoryImpl): TelegramRepository
}