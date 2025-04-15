package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.AlertsRepository
import dev.stupak.usecase.usecase.DeleteActiveAlertsInfoUseCase
import javax.inject.Inject

class DeleteActiveAlertsInfoUseCaseImpl
    @Inject
    constructor(
        private val alertsRepository: AlertsRepository,
    ) : DeleteActiveAlertsInfoUseCase {
        override suspend operator fun invoke(titles: List<String>) {
            alertsRepository.deleteActiveAlertsInfo(titles)
        }
    }
