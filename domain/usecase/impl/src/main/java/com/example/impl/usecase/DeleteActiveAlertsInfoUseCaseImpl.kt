package com.example.impl.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.usecase.usecase.DeleteActiveAlertsInfoUseCase
import dev.stupak.repository.AlertsRepository
import javax.inject.Inject

class DeleteActiveAlertsInfoUseCaseImpl
@Inject
constructor(
    private val alertsRepository: AlertsRepository
) : DeleteActiveAlertsInfoUseCase {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(titles: List<String>) {
        alertsRepository.deleteActiveAlertsInfo(titles)
    }
}
