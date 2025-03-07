package dev.stupak.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import dev.stupak.domain.model.DomainAlertsList
import dev.stupak.domain.model.toDomainModel
import dev.stupak.domain.utils.ResultWrapper
import dev.stupak.repository.AlertsRepository
import javax.inject.Inject

class GetAlertsHistoryUseCase
    @Inject
    constructor(
        private val alertsRepository: AlertsRepository
    ) {
        @RequiresApi(Build.VERSION_CODES.O)
        suspend operator fun invoke(uid: Int, period: String): Result<DomainAlertsList> {
            return ResultWrapper.wrap {
                alertsRepository.getAlertsForPeriod(uid, period).toDomainModel()
            }
        }
    }