package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.impl.mapper.toDomainModel
import com.example.usecase.model.DomainAlertsList
import com.example.usecase.usecase.GetActiveAlertsInfoUseCase
import dev.stupak.repository.AlertsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetActiveAlertsInfoUseCaseImpl
@Inject
constructor(
    private val alertsRepository: AlertsRepository
) : GetActiveAlertsInfoUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(): Flow<Result<DomainAlertsList>> {
        return alertsRepository.getActiveAlertsInfo()
            .map { repositoryList ->
                Result.success(repositoryList.toDomainModel())
            }
            .catch { exception ->
                emit(Result.failure(exception))
            }
    }
}
