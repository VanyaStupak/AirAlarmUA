package dev.stupak.test.impl

import dev.stupak.repository.SettingsRepository
import dev.stupak.usecase.impl.mapper.toRepositoryModel
import dev.stupak.usecase.impl.usecase.UpdateAppSettingsUseCaseImpl
import dev.stupak.usecase.model.SettingsDomainModel
import dev.stupak.usecase.usecase.UpdateAppSettingsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UpdateAppSettingsUseCaseTest {
    private lateinit var useCase: UpdateAppSettingsUseCase
    private val settingsRepository: SettingsRepository = mockk()

    @Before
    fun setUp() {
        useCase = UpdateAppSettingsUseCaseImpl(settingsRepository)
    }

    @Test
    fun `invoke should update settings in repository`() =
        runTest {
            val settingsDomainModel =
                SettingsDomainModel(
                    notifications = true,
                    alertsNotifications = true,
                    telegramNotifications = false,
                    region = "Дніпропетровська область",
                    theme = SettingsDomainModel.Theme.DARK,
                )
            val repositoryModel = settingsDomainModel.toRepositoryModel()

            coEvery { settingsRepository.updateSettings(repositoryModel) } returns Unit

            useCase.invoke(settingsDomainModel)

            coVerify { settingsRepository.updateSettings(repositoryModel) }
        }

    @Test
    fun `invoke should handle error if repository throws an exception`() =
        runTest {
            val settingsDomainModel =
                SettingsDomainModel(
                    notifications = true,
                    alertsNotifications = true,
                    telegramNotifications = false,
                    region = "Дніпропетровська область",
                    theme = SettingsDomainModel.Theme.DARK,
                )
            val repositoryModel = settingsDomainModel.toRepositoryModel()
            coEvery { settingsRepository.updateSettings(repositoryModel) } throws Exception("Update failed")

            try {
                useCase.invoke(settingsDomainModel)
            } catch (exception: Exception) {
                assertEquals("Update failed", exception.message)
            }
        }
}
