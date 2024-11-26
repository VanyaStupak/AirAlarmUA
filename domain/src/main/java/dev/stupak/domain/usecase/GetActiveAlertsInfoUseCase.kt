package dev.stupak.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import dev.stupak.domain.model.DomainAlertsList
import dev.stupak.domain.model.toDomainModel
import dev.stupak.repository.AlertsRepository
import javax.inject.Inject

class GetActiveAlertsInfoUseCase
    @Inject
    constructor(
        private val alertsRepository: AlertsRepository
    ) {
        @RequiresApi(Build.VERSION_CODES.O)
        suspend operator fun invoke(): Result<DomainAlertsList> {
            return try {
                val result = alertsRepository.getActiveAlertsInfo()
                val data = result.getOrNull()
                if (data != null) {
                    Result.success(data.toDomainModel())
                } else {
                    Result.failure(IllegalStateException("No data available"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure(e)
            }
        }
    }