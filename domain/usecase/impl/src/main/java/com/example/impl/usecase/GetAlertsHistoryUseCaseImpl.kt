package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.impl.mapper.toDomainModel
import com.example.usecase.model.DomainAlertsList
import com.example.usecase.usecase.GetAlertsHistoryUseCase
import dev.stupak.repository.AlertsRepository
import javax.inject.Inject

class GetAlertsHistoryUseCaseImpl
@Inject
constructor(
    private val alertsRepository: AlertsRepository
) : GetAlertsHistoryUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(uid: Int, period: String): Result<DomainAlertsList> {
        return runCatching {
            alertsRepository.getAlertsForPeriod(uid, period).toDomainModel()
        }
    }
}