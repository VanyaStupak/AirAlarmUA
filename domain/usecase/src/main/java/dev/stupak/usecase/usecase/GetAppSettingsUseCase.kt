package dev.stupak.usecase.usecase

import dev.stupak.usecase.model.SettingsDomainModel

interface GetAppSettingsUseCase {
    suspend operator fun invoke(): Result<SettingsDomainModel>
}
