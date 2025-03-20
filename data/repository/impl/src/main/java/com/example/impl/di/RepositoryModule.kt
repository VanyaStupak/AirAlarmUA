package com.example.impl.di

import com.example.impl.AlertsRepositoryImpl
import com.example.impl.SettingsRepositoryImpl
import com.example.impl.TelegramRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.stupak.repository.AlertsRepository
import dev.stupak.repository.SettingsRepository
import dev.stupak.repository.TelegramRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindAlertsRepository(impl: AlertsRepositoryImpl): AlertsRepository

    @Singleton
    @Binds
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository

    @Singleton
    @Binds
    abstract fun bindTelegramRepository(impl: TelegramRepositoryImpl): TelegramRepository
}