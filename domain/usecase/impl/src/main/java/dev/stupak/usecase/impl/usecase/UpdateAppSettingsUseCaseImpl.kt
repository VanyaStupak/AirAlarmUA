package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.SettingsRepository
import dev.stupak.usecase.impl.mapper.toRepositoryModel
import dev.stupak.usecase.model.SettingsDomainModel
import dev.stupak.usecase.usecase.UpdateAppSettingsUseCase
import javax.inject.Inject

class UpdateAppSettingsUseCaseImpl
    @Inject
    constructor(
        private val settingsRepository: SettingsRepository,
    ) : UpdateAppSettingsUseCase {
        override suspend operator fun invoke(settings: SettingsDomainModel) {
            settingsRepository.updateSettings(settings.toRepositoryModel())
        }
    }
