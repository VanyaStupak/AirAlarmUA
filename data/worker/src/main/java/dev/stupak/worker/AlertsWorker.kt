package dev.stupak.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.stupak.repository.AlertsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//@HiltWorker
//class AlertsWorker @AssistedInject constructor(
//    @Assisted context: Context,
//    @Assisted workerParams: WorkerParameters,
//    private val alertsRepository: AlertsRepository
//) : CoroutineWorker(context, workerParams) {
//
//    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
//        val result = alertsRepository.getActiveAlertsInfo()
//        if (result.isSuccess) {
//            Result.success()
//        } else {
//            Result.retry()
//        }
//    }
//}
