package dev.stupak.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import dev.stupak.domain.model.SettingsDomainModel
import dev.stupak.domain.model.toRepositoryModel
import dev.stupak.repository.SettingsRepository
import javax.inject.Inject

class UpdateAppSettingsUseCase
@Inject
constructor(
    private val settingsRepository: SettingsRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(settings: SettingsDomainModel) {
        settingsRepository.updateSettings(settings.toRepositoryModel())
    }
}