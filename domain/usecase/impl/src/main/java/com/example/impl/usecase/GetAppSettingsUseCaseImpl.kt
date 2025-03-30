package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.impl.mapper.toDomainModel
import com.example.usecase.model.SettingsDomainModel
import com.example.usecase.usecase.GetAppSettingsUseCase
import dev.stupak.repository.SettingsRepository
import javax.inject.Inject

class GetAppSettingsUseCaseImpl
@Inject
constructor(
    private val settingsRepository: SettingsRepository
) : GetAppSettingsUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(): Result<SettingsDomainModel> {
        return runCatching {
            settingsRepository.getSettings().toDomainModel()
        }
    }
}