package dev.stupak.network.service

import dev.stupak.network.model.AlertsList

interface AlertsApiService {

    suspend fun getActiveAlerts(): Result<String>

    suspend fun getActiveAlertsInfo(): Result<AlertsList>

    suspend fun getAlertsForPeriod(uid:Int = 31,period:String = "month_ago"): Result<AlertsList>

}