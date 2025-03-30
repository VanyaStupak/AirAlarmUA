package com.example.impl.di

import com.example.impl.usecase.*
import com.example.usecase.usecase.DeleteActiveAlertsInfoUseCase
import com.example.usecase.usecase.DeleteTelegramMessagesUseCase
import com.example.usecase.usecase.GetActiveAlertsInfoFromNetUseCase
import com.example.usecase.usecase.GetActiveAlertsInfoUseCase
import com.example.usecase.usecase.GetAlertsHistoryUseCase
import com.example.usecase.usecase.GetAppSettingsUseCase
import com.example.usecase.usecase.GetTelegramMessagesFromNetUseCase
import com.example.usecase.usecase.GetTelegramMessagesUseCase
import com.example.usecase.usecase.InsertActiveAlertsInfoUseCase
import com.example.usecase.usecase.InsertTelegramMessagesUseCase
import com.example.usecase.usecase.UpdateAppSettingsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetActiveAlertsInfoUseCase(
        getActiveAlertsInfoUseCaseImpl: GetActiveAlertsInfoUseCaseImpl
    ): GetActiveAlertsInfoUseCase

    @Binds
    abstract fun bindDeleteActiveAlertsInfoUseCase(
        deleteActiveAlertsInfoUseCaseImpl: DeleteActiveAlertsInfoUseCaseImpl
    ): DeleteActiveAlertsInfoUseCase

    @Binds
    abstract fun bindDeleteTelegramMessagesUseCase(
        deleteTelegramMessagesUseCaseImpl: DeleteTelegramMessagesUseCaseImpl
    ): DeleteTelegramMessagesUseCase

    @Binds
    abstract fun bindGetActiveAlertsInfoFromNetUseCase(
        getActiveAlertsInfoFromNetUseCaseImpl: GetActiveAlertsInfoFromNetUseCaseImpl
    ): GetActiveAlertsInfoFromNetUseCase

    @Binds
    abstract fun bindGetAlertsHistoryUseCase(
        getAlertsHistoryUseCaseImpl: GetAlertsHistoryUseCaseImpl
    ): GetAlertsHistoryUseCase

    @Binds
    abstract fun bindGetAppSettingsUseCase(
        getAppSettingsUseCaseImpl: GetAppSettingsUseCaseImpl
    ): GetAppSettingsUseCase

    @Binds
    abstract fun bindGetTelegramMessagesFromNetUseCase(
        getTelegramMessagesFromNetUseCaseImpl: GetTelegramMessagesFromNetUseCaseImpl
    ): GetTelegramMessagesFromNetUseCase

    @Binds
    abstract fun bindGetTelegramMessagesUseCase(
        getTelegramMessagesUseCaseImpl: GetTelegramMessagesUseCaseImpl
    ): GetTelegramMessagesUseCase

    @Binds
    abstract fun bindInsertActiveAlertsInfoUseCase(
        insertActiveAlertsInfoUseCaseImpl: InsertActiveAlertsInfoUseCaseImpl
    ): InsertActiveAlertsInfoUseCase

    @Binds
    abstract fun bindInsertTelegramMessagesUseCase(
        insertTelegramMessagesUseCaseImpl: InsertTelegramMessagesUseCaseImpl
    ): InsertTelegramMessagesUseCase

    @Binds
    abstract fun bindUpdateAppSettingsUseCase(
        updateAppSettingsUseCaseImpl: UpdateAppSettingsUseCaseImpl
    ): UpdateAppSettingsUseCase
}