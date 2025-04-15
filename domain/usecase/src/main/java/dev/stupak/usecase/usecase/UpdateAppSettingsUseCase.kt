package dev.stupak.usecase.usecase

import dev.stupak.usecase.model.SettingsDomainModel

interface UpdateAppSettingsUseCase {
    suspend operator fun invoke(settings: SettingsDomainModel)
}
