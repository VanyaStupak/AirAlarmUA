package dev.stupak.usecase.usecase

import dev.stupak.usecase.model.DomainAlertsList

interface InsertActiveAlertsInfoUseCase {
    suspend operator fun invoke(alerts: DomainAlertsList)
}
