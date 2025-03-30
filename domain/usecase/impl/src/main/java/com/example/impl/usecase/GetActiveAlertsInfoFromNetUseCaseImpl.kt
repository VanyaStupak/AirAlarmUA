package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.impl.mapper.toDomainModel
import com.example.usecase.model.DomainAlertsList
import com.example.usecase.usecase.GetActiveAlertsInfoFromNetUseCase
import dev.stupak.repository.AlertsRepository
import javax.inject.Inject

class GetActiveAlertsInfoFromNetUseCaseImpl
@Inject
constructor(
    private val alertsRepository: AlertsRepository
): GetActiveAlertsInfoFromNetUseCase {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(): Result<DomainAlertsList> {
        return runCatching {
            alertsRepository.getActiveAlertsInfoFromNet().toDomainModel()
        }
    }
}

