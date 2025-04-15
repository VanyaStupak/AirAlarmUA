package dev.stupak.usecase.usecase

import dev.stupak.usecase.model.DomainAlertsList

interface GetActiveAlertsInfoFromNetUseCase {
    suspend operator fun invoke(): Result<DomainAlertsList>
}
