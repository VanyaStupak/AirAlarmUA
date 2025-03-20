package dev.stupak.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import dev.stupak.domain.model.DomainAlertsList
import dev.stupak.domain.model.toDomainModel
import dev.stupak.domain.model.toRepositoryAlertsList
import dev.stupak.repository.AlertsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InsertActiveAlertsInfoUseCase
@Inject
constructor(
    private val alertsRepository: AlertsRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(alerts: DomainAlertsList) {
        return alertsRepository.insertActiveAlertsInfo(alerts.toRepositoryAlertsList())
    }
}
