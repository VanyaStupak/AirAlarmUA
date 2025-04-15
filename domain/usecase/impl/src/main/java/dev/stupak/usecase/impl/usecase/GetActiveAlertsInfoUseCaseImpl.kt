package dev.stupak.usecase.impl.usecase

import dev.stupak.repository.AlertsRepository
import dev.stupak.usecase.impl.mapper.toDomainModel
import dev.stupak.usecase.model.DomainAlertsList
import dev.stupak.usecase.usecase.GetActiveAlertsInfoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetActiveAlertsInfoUseCaseImpl
    @Inject
    constructor(
        private val alertsRepository: AlertsRepository,
    ) : GetActiveAlertsInfoUseCase {
        override suspend operator fun invoke(): Flow<Result<DomainAlertsList>> =
            alertsRepository
                .getActiveAlertsInfo()
                .map { repositoryList ->
                    Result.success(repositoryList.toDomainModel())
                }.catch { exception ->
                    emit(Result.failure(exception))
                }
    }
