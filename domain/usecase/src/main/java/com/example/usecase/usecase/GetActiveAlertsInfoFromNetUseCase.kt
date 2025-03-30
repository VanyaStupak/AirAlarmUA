package com.example.usecase.usecase

import com.example.usecase.model.DomainAlertsList

interface GetActiveAlertsInfoFromNetUseCase {
    suspend operator fun invoke(): Result<DomainAlertsList>
}