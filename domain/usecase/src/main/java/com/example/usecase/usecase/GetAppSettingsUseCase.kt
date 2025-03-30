package com.example.usecase.usecase

import com.example.usecase.model.SettingsDomainModel

interface GetAppSettingsUseCase {
    suspend operator fun invoke(): Result<SettingsDomainModel>
}