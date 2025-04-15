package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.AlertsRepository
import dev.stupak.usecase.impl.mapper.toDomainModel
import dev.stupak.usecase.model.DomainAlertsList
import dev.stupak.usecase.resultLauncher
import dev.stupak.usecase.usecase.GetAlertsHistoryUseCase
import javax.inject.Inject

class GetAlertsHistoryUseCaseImpl
    @Inject
    constructor(
        private val alertsRepository: AlertsRepository,
    ) : GetAlertsHistoryUseCase {
        override suspend operator fun invoke(
            uid: Int,
            period: String,
        ): Result<DomainAlertsList> =
            resultLauncher {
                alertsRepository.getAlertsForPeriod(uid, period).toDomainModel()
            }
    }
