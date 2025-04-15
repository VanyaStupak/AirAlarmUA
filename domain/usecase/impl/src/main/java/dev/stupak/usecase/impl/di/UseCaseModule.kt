package dev.stupak.usecase.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.stupak.usecase.impl.usecase.DeleteActiveAlertsInfoUseCaseImpl
import dev.stupak.usecase.impl.usecase.DeleteTelegramMessagesUseCaseImpl
import dev.stupak.usecase.impl.usecase.GetActiveAlertsInfoFromNetUseCaseImpl
import dev.stupak.usecase.impl.usecase.GetActiveAlertsInfoUseCaseImpl
import dev.stupak.usecase.impl.usecase.GetAlertsHistoryUseCaseImpl
import dev.stupak.usecase.impl.usecase.GetAppSettingsUseCaseImpl
import dev.stupak.usecase.impl.usecase.GetTelegramMessagesFromNetUseCaseImpl
import dev.stupak.usecase.impl.usecase.GetTelegramMessagesUseCaseImpl
import dev.stupak.usecase.impl.usecase.InsertActiveAlertsInfoUseCaseImpl
import dev.stupak.usecase.impl.usecase.InsertTelegramMessagesUseCaseImpl
import dev.stupak.usecase.impl.usecase.UpdateAppSettingsUseCaseImpl
import dev.stupak.usecase.usecase.DeleteActiveAlertsInfoUseCase
import dev.stupak.usecase.usecase.DeleteTelegramMessagesUseCase
import dev.stupak.usecase.usecase.GetActiveAlertsInfoFromNetUseCase
import dev.stupak.usecase.usecase.GetActiveAlertsInfoUseCase
import dev.stupak.usecase.usecase.GetAlertsHistoryUseCase
import dev.stupak.usecase.usecase.GetAppSettingsUseCase
import dev.stupak.usecase.usecase.GetTelegramMessagesFromNetUseCase
import dev.stupak.usecase.usecase.GetTelegramMessagesUseCase
import dev.stupak.usecase.usecase.InsertActiveAlertsInfoUseCase
import dev.stupak.usecase.usecase.InsertTelegramMessagesUseCase
import dev.stupak.usecase.usecase.UpdateAppSettingsUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetActiveAlertsInfoUseCase(getActiveAlertsInfoUseCaseImpl: GetActiveAlertsInfoUseCaseImpl): GetActiveAlertsInfoUseCase

    @Binds
    abstract fun bindDeleteActiveAlertsInfoUseCase(
        deleteActiveAlertsInfoUseCaseImpl: DeleteActiveAlertsInfoUseCaseImpl,
    ): DeleteActiveAlertsInfoUseCase

    @Binds
    abstract fun bindDeleteTelegramMessagesUseCase(
        deleteTelegramMessagesUseCaseImpl: DeleteTelegramMessagesUseCaseImpl,
    ): DeleteTelegramMessagesUseCase

    @Binds
    abstract fun bindGetActiveAlertsInfoFromNetUseCase(
        getActiveAlertsInfoFromNetUseCaseImpl: GetActiveAlertsInfoFromNetUseCaseImpl,
    ): GetActiveAlertsInfoFromNetUseCase

    @Binds
    abstract fun bindGetAlertsHistoryUseCase(getAlertsHistoryUseCaseImpl: GetAlertsHistoryUseCaseImpl): GetAlertsHistoryUseCase

    @Binds
    abstract fun bindGetAppSettingsUseCase(getAppSettingsUseCaseImpl: GetAppSettingsUseCaseImpl): GetAppSettingsUseCase

    @Binds
    abstract fun bindGetTelegramMessagesFromNetUseCase(
        getTelegramMessagesFromNetUseCaseImpl: GetTelegramMessagesFromNetUseCaseImpl,
    ): GetTelegramMessagesFromNetUseCase

    @Binds
    abstract fun bindGetTelegramMessagesUseCase(getTelegramMessagesUseCaseImpl: GetTelegramMessagesUseCaseImpl): GetTelegramMessagesUseCase

    @Binds
    abstract fun bindInsertActiveAlertsInfoUseCase(
        insertActiveAlertsInfoUseCaseImpl: InsertActiveAlertsInfoUseCaseImpl,
    ): InsertActiveAlertsInfoUseCase

    @Binds
    abstract fun bindInsertTelegramMessagesUseCase(
        insertTelegramMessagesUseCaseImpl: InsertTelegramMessagesUseCaseImpl,
    ): InsertTelegramMessagesUseCase

    @Binds
    abstract fun bindUpdateAppSettingsUseCase(updateAppSettingsUseCaseImpl: UpdateAppSettingsUseCaseImpl): UpdateAppSettingsUseCase
}
