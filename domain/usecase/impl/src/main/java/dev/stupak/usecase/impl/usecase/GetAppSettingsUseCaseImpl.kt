package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.SettingsRepository
import dev.stupak.usecase.impl.mapper.toDomainModel
import dev.stupak.usecase.model.SettingsDomainModel
import dev.stupak.usecase.resultLauncher
import dev.stupak.usecase.usecase.GetAppSettingsUseCase
import javax.inject.Inject

class GetAppSettingsUseCaseImpl
    @Inject
    constructor(
        private val settingsRepository: SettingsRepository,
    ) : GetAppSettingsUseCase {
        override suspend operator fun invoke(): Result<SettingsDomainModel> =
            resultLauncher {
                settingsRepository.getSettings().toDomainModel()
            }
    }
