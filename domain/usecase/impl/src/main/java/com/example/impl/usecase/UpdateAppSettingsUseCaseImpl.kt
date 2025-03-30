package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.impl.mapper.toRepositoryModel
import com.example.usecase.model.SettingsDomainModel
import com.example.usecase.usecase.UpdateAppSettingsUseCase
import dev.stupak.repository.SettingsRepository
import javax.inject.Inject

class UpdateAppSettingsUseCaseImpl
@Inject
constructor(
    private val settingsRepository: SettingsRepository
) : UpdateAppSettingsUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(settings: SettingsDomainModel) {
        settingsRepository.updateSettings(settings.toRepositoryModel())
    }
}