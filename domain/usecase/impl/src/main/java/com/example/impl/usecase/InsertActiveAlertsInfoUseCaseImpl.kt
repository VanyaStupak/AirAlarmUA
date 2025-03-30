package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.impl.mapper.toRepositoryAlertsList
import com.example.usecase.model.DomainAlertsList
import com.example.usecase.usecase.InsertActiveAlertsInfoUseCase
import dev.stupak.repository.AlertsRepository
import javax.inject.Inject

class InsertActiveAlertsInfoUseCaseImpl
@Inject
constructor(
    private val alertsRepository: AlertsRepository
) : InsertActiveAlertsInfoUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(alerts: DomainAlertsList) {
        alertsRepository.insertActiveAlertsInfo(alerts.toRepositoryAlertsList())
    }
}
