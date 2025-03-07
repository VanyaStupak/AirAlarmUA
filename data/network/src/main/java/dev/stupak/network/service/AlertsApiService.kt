package dev.stupak.network.service

import dev.stupak.network.model.AlertsList

interface AlertsApiService {

    suspend fun getActiveAlertsInfo(): AlertsList

    suspend fun getAlertsForPeriod(uid :Int = 31,period: String = "month_ago"): AlertsList

}