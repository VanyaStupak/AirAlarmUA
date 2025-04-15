package dev.stupak.usecase.usecase

import dev.stupak.usecase.model.DomainAlertsList
import kotlinx.coroutines.flow.Flow

interface GetActiveAlertsInfoUseCase {
    suspend operator fun invoke(): Flow<Result<DomainAlertsList>>
}
