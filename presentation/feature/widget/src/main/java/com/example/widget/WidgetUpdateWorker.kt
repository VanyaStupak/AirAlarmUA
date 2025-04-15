package com.example.widget

import android.content.Context
import android.net.NetworkCapabilities
import androidx.glance.appwidget.updateAll
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.stupak.platform.ConnectivityObserver

class WidgetUpdateWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val isConnected = isNetworkConnected()
        val prefs = applicationContext.getSharedPreferences("alerts_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("is_connected", isConnected).apply()
        MyGlanceWidget().updateAll(applicationContext)
        return Result.success()
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityObserver = ConnectivityObserver(applicationContext)
        val network = connectivityObserver.connectivityManager.activeNetwork
        val networkCapabilities = connectivityObserver.connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) == true
    }
}
