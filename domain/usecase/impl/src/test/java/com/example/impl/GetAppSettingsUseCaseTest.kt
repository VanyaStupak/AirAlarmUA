package com.example.impl

import dev.stupak.repository.SettingsRepository
import dev.stupak.repository.model.SettingsRepositoryModel
import dev.stupak.usecase.impl.usecase.GetAppSettingsUseCaseImpl
import dev.stupak.usecase.model.SettingsDomainModel
import dev.stupak.usecase.usecase.GetAppSettingsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetAppSettingsUseCaseTest {
    private lateinit var useCase: GetAppSettingsUseCase
    private val settingsRepository: SettingsRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetAppSettingsUseCaseImpl(settingsRepository)
    }

    @Test
    fun `invoke should return success with transformed data`() =
        runTest {
            val settingsDomainModel =
                SettingsDomainModel(
                    notifications = true,
                    alertsNotifications = true,
                    telegramNotifications = false,
                    region = "Дніпропетровьска область",
                    theme = SettingsDomainModel.Theme.DARK,
                )
            val repositorySettings =
                SettingsRepositoryModel(
                    notifications = true,
                    alertsNotifications = true,
                    telegramNotifications = false,
                    region = "Дніпропетровьска область",
                    theme = SettingsRepositoryModel.Theme.DARK,
                )

            coEvery { settingsRepository.getSettings() } returns repositorySettings

            val result: Result<SettingsDomainModel> = useCase.invoke()

            assertTrue(result.isSuccess)
            assertEquals(settingsDomainModel, result.getOrNull())
        }

    @Test
    fun `invoke should return failure when repository throws an exception`() =
        runTest {
            val exception = IOException("Network error")

            coEvery { settingsRepository.getSettings() } throws exception

            val result: Result<SettingsDomainModel> = useCase.invoke()

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
        }
}
