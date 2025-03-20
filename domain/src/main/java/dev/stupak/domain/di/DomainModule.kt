package dev.stupak.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.stupak.domain.usecase.GetActiveAlertsInfoUseCase
import dev.stupak.domain.usecase.GetAppSettingsUseCase
import dev.stupak.repository.AlertsRepository
import dev.stupak.repository.SettingsRepository

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideGetActiveAlertsInfoUseCase(
        repository: AlertsRepository
    ): GetActiveAlertsInfoUseCase {
        return GetActiveAlertsInfoUseCase(repository)
    }

    @Provides
    fun provideGetAppSettingsUseCase(
        repository: SettingsRepository
    ): GetAppSettingsUseCase {
        return GetAppSettingsUseCase(repository)
    }

}
