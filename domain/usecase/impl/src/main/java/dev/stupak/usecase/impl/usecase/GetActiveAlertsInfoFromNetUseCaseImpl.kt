package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.AlertsRepository
import dev.stupak.usecase.impl.mapper.toDomainModel
import dev.stupak.usecase.model.DomainAlertsList
import dev.stupak.usecase.resultLauncher
import dev.stupak.usecase.usecase.GetActiveAlertsInfoFromNetUseCase
import javax.inject.Inject

class GetActiveAlertsInfoFromNetUseCaseImpl
    @Inject
    constructor(
        private val alertsRepository: AlertsRepository,
    ) : GetActiveAlertsInfoFromNetUseCase {
        override suspend operator fun invoke(): Result<DomainAlertsList> =
            resultLauncher {
                alertsRepository.getActiveAlertsInfoFromNet().toDomainModel()
            }
    }
