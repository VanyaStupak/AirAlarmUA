package com.example.usecase.usecase

import com.example.usecase.model.DomainAlertsList

interface GetAlertsHistoryUseCase {
    suspend operator fun invoke(uid: Int, period: String): Result<DomainAlertsList>
}