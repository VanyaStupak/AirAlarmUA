package com.example.usecase.usecase

import com.example.usecase.model.DomainAlertsList

interface InsertActiveAlertsInfoUseCase {
    suspend operator fun invoke(alerts: DomainAlertsList)
}