package dev.stupak.domain.usecase

//import android.content.Context
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.work.ExistingPeriodicWorkPolicy
//import androidx.work.PeriodicWorkRequestBuilder
//import androidx.work.WorkManager
//import dev.stupak.worker.AlertsWorker
//import java.util.concurrent.TimeUnit
//import javax.inject.Inject
//
//class StartPeriodicWorkerUseCase @Inject constructor() {
//    @RequiresApi(Build.VERSION_CODES.O)
//    operator fun invoke(context: Context) {
//        val workRequest = PeriodicWorkRequestBuilder<AlertsWorker>(1, TimeUnit.MINUTES)
//            .build()
//
//        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
//            "FetchAlertsWorker",
//            ExistingPeriodicWorkPolicy.KEEP,
//            workRequest
//        )
//    }
//}