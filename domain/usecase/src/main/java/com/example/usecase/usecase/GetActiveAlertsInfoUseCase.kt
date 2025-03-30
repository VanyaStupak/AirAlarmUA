package com.example.usecase.usecase

import com.example.usecase.model.DomainAlertsList
import kotlinx.coroutines.flow.Flow

interface GetActiveAlertsInfoUseCase {
    suspend operator fun invoke(): Flow<Result<DomainAlertsList>>
}