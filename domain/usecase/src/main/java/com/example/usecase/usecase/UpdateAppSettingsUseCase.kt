package com.example.usecase.usecase

import com.example.usecase.model.SettingsDomainModel

interface UpdateAppSettingsUseCase {
    suspend operator fun invoke(settings: SettingsDomainModel)
}