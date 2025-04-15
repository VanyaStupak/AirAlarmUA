package dev.stupak.usecase.usecase

import dev.stupak.usecase.model.DomainAlertsList

interface GetAlertsHistoryUseCase {
    suspend operator fun invoke(
        uid: Int,
        period: String,
    ): Result<DomainAlertsList>
}
