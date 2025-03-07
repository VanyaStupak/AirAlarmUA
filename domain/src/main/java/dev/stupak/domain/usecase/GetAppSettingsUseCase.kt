package dev.stupak.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import dev.stupak.domain.model.SettingsDomainModel
import dev.stupak.domain.model.toDomainModel
import dev.stupak.domain.utils.ResultWrapper
import dev.stupak.repository.SettingsRepository
import javax.inject.Inject

class GetAppSettingsUseCase
    @Inject
    constructor(
        private val settingsRepository: SettingsRepository
    ) {
        @RequiresApi(Build.VERSION_CODES.O)
        suspend operator fun invoke(): Result<SettingsDomainModel> {
            return ResultWrapper.wrap {
                 settingsRepository.getSettings().toDomainModel()
            }
        }
    }