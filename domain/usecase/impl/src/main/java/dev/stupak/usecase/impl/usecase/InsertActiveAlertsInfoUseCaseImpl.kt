package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.AlertsRepository
import dev.stupak.usecase.impl.mapper.toRepositoryAlertsList
import dev.stupak.usecase.model.DomainAlertsList
import dev.stupak.usecase.usecase.InsertActiveAlertsInfoUseCase
import javax.inject.Inject

class InsertActiveAlertsInfoUseCaseImpl
    @Inject
    constructor(
        private val alertsRepository: AlertsRepository,
    ) : InsertActiveAlertsInfoUseCase {
        override suspend operator fun invoke(alerts: DomainAlertsList) {
            alertsRepository.insertActiveAlertsInfo(alerts.toRepositoryAlertsList())
        }
    }
